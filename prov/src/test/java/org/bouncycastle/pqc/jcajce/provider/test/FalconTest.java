package org.bouncycastle.pqc.jcajce.provider.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import junit.framework.TestCase;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.pqc.crypto.test.NISTSecureRandom;
import org.bouncycastle.pqc.jcajce.interfaces.FalconKey;
import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.bouncycastle.pqc.jcajce.spec.FalconParameterSpec;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

public class FalconTest
    extends TestCase
{
    byte[] msg = Strings.toByteArray("Hello World!");

    public void setUp()
    {
        if (Security.getProvider(BouncyCastlePQCProvider.PROVIDER_NAME) == null)
        {
            Security.addProvider(new BouncyCastlePQCProvider());
        }
    }

    public void testPrivateKeyRecovery()
            throws Exception
    {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Falcon", "BCPQC");

        kpg.initialize(FalconParameterSpec.falcon_512, new FalconTest.RiggedRandom());

        KeyPair kp = kpg.generateKeyPair();

        KeyFactory kFact = KeyFactory.getInstance("Falcon", "BCPQC");

        FalconKey privKey = (FalconKey)kFact.generatePrivate(new PKCS8EncodedKeySpec(kp.getPrivate().getEncoded()));

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ObjectOutputStream oOut = new ObjectOutputStream(bOut);

        oOut.writeObject(privKey);

        oOut.close();

        ObjectInputStream oIn = new ObjectInputStream(new ByteArrayInputStream(bOut.toByteArray()));

        FalconKey privKey2 = (FalconKey)oIn.readObject();

        assertEquals(privKey, privKey2);
    }

    public void testPublicKeyRecovery()
            throws Exception
    {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Falcon", "BCPQC");

        kpg.initialize(FalconParameterSpec.falcon_1024, new FalconTest.RiggedRandom());

        KeyPair kp = kpg.generateKeyPair();

        KeyFactory kFact = KeyFactory.getInstance("Falcon", "BCPQC");

        FalconKey pubKey = (FalconKey)kFact.generatePublic(new X509EncodedKeySpec(kp.getPublic().getEncoded()));

        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ObjectOutputStream oOut = new ObjectOutputStream(bOut);

        oOut.writeObject(pubKey);

        oOut.close();

        ObjectInputStream oIn = new ObjectInputStream(new ByteArrayInputStream(bOut.toByteArray()));

        FalconKey pubKey2 = (FalconKey)oIn.readObject();

        assertEquals(pubKey, pubKey2);
    }

    public void testFalconRandomSig()
            throws Exception
    {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Falcon", "BCPQC");

        kpg.initialize(FalconParameterSpec.falcon_512, new SecureRandom());

        KeyPair kp = kpg.generateKeyPair();

        Signature sig = Signature.getInstance("Falcon", "BCPQC");

        sig.initSign(kp.getPrivate(), new SecureRandom());

        sig.update(msg, 0, msg.length);

        byte[] s = sig.sign();

        sig = Signature.getInstance("Falcon", "BCPQC");

        sig.initVerify(kp.getPublic());

        sig.update(msg, 0, msg.length);

        assertTrue(sig.verify(s));
    }

    /**
     * count = 0
     * seed = 061550234D158C5EC95595FE04EF7A25767F2E24CC2BC479D09D86DC9ABCFDE7056A8C266F9EF97ED08541DBD2E1FFA1
     * mlen = 33
     * msg = D81C4D8D734FCBFBEADE3D3F8A039FAA2A2C9957E835AD55B22E75BF57BB556AC8
     * pk = 096BA86CB658A8F445C9A5E4C28374BEC879C8655F68526923240918074D0147C03162E4A49200648C652803C6FD7509AE9AA799D6310D0BD42724E0635920186207000767CA5A8546B1755308C304B84FC93B069E265985B398D6B834698287FF829AA820F17A7F4226AB21F601EBD7175226BAB256D8888F009032566D6383D68457EA155A94301870D589C678ED304259E9D37B193BC2A7CCBCBEC51D69158C44073AEC9792630253318BC954DBF50D15028290DC2D309C7B7B02A6823744D463DA17749595CB77E6D16D20D1B4C3AAD89D320EBE5A672BB96D6CD5C1EFEC8B811200CBB062E473352540EDDEF8AF9499F8CDD1DC7C6873F0C7A6BCB7097560271F946849B7F373640BB69CA9B518AA380A6EB0A7275EE84E9C221AED88F5BFBAF43A3EDE8E6AA42558104FAF800E018441930376C6F6E751569971F47ADBCA5CA00C801988F317A18722A29298925EA154DBC9024E120524A2D41DC0F18FD8D909F6C50977404E201767078BA9A1F9E40A8B2BA9C01B7DA3A0B73A4C2A6B4F518BBEE3455D0AF2204DDC031C805C72CCB647940B1E6794D859AAEBCEA0DEB581D61B9248BD9697B5CB974A8176E8F910469CAE0AB4ED92D2AEE9F7EB50296DAF8057476305C1189D1D9840A0944F0447FB81E511420E67891B98FA6C257034D5A063437D379177CE8D3FA6EAF12E2DBB7EB8E498481612B1929617DA5FB45E4CDF893927D8BA842AA861D9C50471C6D0C6DF7E2BB26465A0EB6A3A709DE792AAFAAF922AA95DD5920B72B4B8856C6E632860B10F5CC08450003671AF388961872B466400ADB815BA81EA794945D19A100622A6CA0D41C4EA620C21DC125119E372418F04402D9FA7180F7BC89AFA54F8082244A42F46E5B5ABCE87B50A7D6FEBE8D7BBBAC92657CBDA1DB7C25572A4C1D0BAEA30447A865A2B1036B880037E2F4D26D453E9E913259779E9169B28A62EB809A5C744E04E260E1F2BBDA874F1AC674839DDB47B3148C5946DE0180148B7973D63C58193B17CD05D16E80CD7928C2A338363A23A81C0608C87505589B9DA1C617E7B70786B6754FBB30A5816810B9E126CFCC5AA49326E9D842973874B6359B5DB75610BA68A98C7B5E83F125A82522E13B83FB8F864E2A97B73B5D544A7415B6504A13939EAB1595D64FAF41FAB25A864A574DE524405E878339877886D2FC07FA0311508252413EDFA1158466667AFF78386DAF7CB4C9B850992F96E20525330599AB601D454688E294C8C3E
     * sk = 59044102F3CFBE1BE03C144102F7EF75FBEF83043F7CFC20C20BEEC007DE3F041FBF0BFF401041030C40040FAE7E103F7E100085FC013D1410C80C2F000810461C2F480BEE8017D17F07F1411BA24013C1BDF83DC407D17E07C13917F0F9044045FC40BD0FF07D07EF0003DFC1F3CFFD1FC03FEFC0B8FC6E7B0BBDBD0FE0BE17D14307EFFE0FBFC6F81FBFF43EC1F87041D42083EC3DC2F4407BF84EC4140FC403F037F3FEC013E0FEE02180082F83FBE07BFFE043F40EC6FFB1BF200007FFBFFA0FFF6FFBCE83EBFEBEFC0FFDF3F103FC6F3FF0500A18718308007D03F200E4213BF04FFD17D000F0017A17F180E04FFF07DEC2244048148E8704503EE06F86080243F81FFF03BF4003F07EF3DE02FBFFC00420C1F40FBDF0707E043FF5FFD0000430400C4F49F4207C142F80EC3E010BFF7C13F07FF85F7F17E07C17FF33FC4EC303FFBCFFEEC41830FF0831BDF45F05F06FC503B0C0F84E4013E100E7E1441450C2FBEEBC0C0FBEFC60BCFFEF3CFBDF4303EF800BF2BE0BF001F01F43F41FFE08517B001141E00144F7EF8007CEBDFFFF4213A0B9F8A0FE04103C17E0820BB1C30C30C00FFFFC00007D18017CFF90C3101E7E103040FC4FBE04213E07AF80FFEFC80FBFBD0810BCFB8FBC087FB8FFF1010C2E81002F3EF3BF01F07E41FBC07F2C0FB8F43F401C5D81FFCEBE07C07E0BF17EEBEE830C514003FF7EF3E08403D1FFFFE105F840C20BDF0607FFFEF46E7EFFF08000400DE830000F3F82EF9D82E84EFFF3CEC4E81E01002103102EFC080F3B0801041BAE42F7F040F83EC31010031BC0410FAFF9F0004010133A089FFEF7BE8317A0020FEF010052BA04107E100F821C2F41F44F4EF7B000F02E41F82F380830FE08A1F707FF82EC7F42E81004041103E8307B13D0FDFF8F830F9FC5FFCD7E040F410FFFB9F423750860C11C5FFA144EC0080F02DC0F420820450790020BCF80EFFFBCEC4FBFF4200AFC00C02060C004303EF81FFA104107E4117AF01F81202FC1E44143FFE206EB3E881BB13F13920403FF7A000144102E7FFC2143E7FF4AF3F13F07E181DC317E240F4500303F2DDDCF1E1513E3EF15E8DC1309E50AEE03EFDC17081706FD03E6ECE4F30EBD1909051906E90CE806EB0B19E719EFFBF10D0DF1DC0CF6F1F4F8FEFBE9F9550E2107FCDCCBDFE9F4F7EE1AF8142115F910002AF2F5FF141ADA220AECFE040CEF0B29EB201930F2D3E401E5DEEFF4DDEA17F1FE141217F81C36050109F8F61F02DD19F90310C7F40208E9052C3942F8FFF2CCF9FDF83CFA12DC091C0D02F00411F5281E40D7F92DBA11D73D04C10BFD13E617110AF3ED05F6CFE705E0F70E1FF80533FC120C002CE81FF52638190FE3FED6F0FBBB23E6F408EF32220B13DD27F007E5FA00D72614F0E302210707EC111E070E2A032DF91DE3FCE800F1F9F2F7FE170101180412CBD1E90019F2011522DAEAED13F8E5F425DCEF24E01CE614E7DCEC01F2F4F914F4010107ED26E2E9DF0BF5F007EA07FAFBC6D7E607FAFCFD270DFD0D17FC4EF0EE00071AECDE09F8F215E113F80209CCF308D7E6251ECE0EDFED0CC9F4050B2714F61BF703F0EBF104010DEBFBF21AFC1BF01823FEDEFAF7F807E3F3020AEB01FE19EEE8E90D00E5FAED1EFDF628E5F0E6F0FC13F4FB05FB0B09EA0A0E08EE13293212E90CE4FEF223F4FF030BEBED1B402ED2F6171102BC0CF9E9F335ED0C01FAF0FEFAE41DF0050A162C11171CD90BEE211218EDFAFA0F03F4171412F319D60B01FAEE1F2823F0D6EF12D6DFEAFBFC170DECDA06E7CED500031E
     * sm = 026833B3C07507E4201748494D832B6EE2A6C93BFF9B0EE343B550D1F85A3D0DE0D704C6D17842951309D81C4D8D734FCBFBEADE3D3F8A039FAA2A2C9957E835AD55B22E75BF57BB556AC8290765843D1E460D17A527D2BCA405BD55BBC7DA09A8C620BE0AF4A767D9DB96B80F55E466676751EAABA7B93B86D71132DAA0EB376782B9EEE37519CE10FDD33FE9F29312C31D8736206D165CF4C528AA3DDC017845E1F0DD5B0A44FF961C42D874A95533E5B438982F524CA954D87533BFBE42C63FF2ABC77A34C79DB55A99171BBCB72C842A6530AF2F753F0C34AC632F9F1E7949F0BF6C67665B27722A8857D626B6FF1A136D923A39F4069B7477FF946E5247A6627791D49B59EDC9E2525A860E6E9828D18F64A9F17222E8166A02453859BBDA0B8186D8C9928BB571E4146401D7430E225904673AD21CCAC54C146C248A1DD69AB6491E901D6D71B152155BE97DE057F3916A3F1B4273308C29B2F4D9697167B90681B1583ED930A71E990467DEA368134BECEEBD597F9BEC922E816F1B0570D728F4AE0464C1F797657F87A4E52DCDCAEB9272662EA66D7C6CD8781B31AF555AD93F5F65E75816CB8DC306BB67E592B5261BACA7C509629EA2AF8ABB80CBA89EE535B76DFD9CCBBE3BF48F2BC8AA34B26E1103291053F5CB8DE3A45AFA5A76DF8B2122ED2C82FBCF2259290D41A14F86B12F35F5D49762B34CFF13EE7E42EDEC70201D7F37C33316288FA3078E36E58108865C3CFE263D563692043DECC62F3426F86061285B7B1B336F56FF41BB65E9CD6D9B92FD90F864AA1C923CB8C755F5CDE1770D862595427149D7721AAAB5D194AEA9ACDECA15BE43CBA6A62B5A33909E9FC4DA1C5814FBD7CD6A2FA572E318B42C6C319140B86E66392580A11A2B431F44C1F9270E4F7B2490F3B325A9977A71A575915636635B9969DBD6D220B24C3D99CEBBBD834B88222BD08C3ABE124E80
     * @throws Exception
     */
    public void testFalconKATSig()
            throws Exception
    {
        byte[] pubK = Hex.decode("096BA86CB658A8F445C9A5E4C28374BEC879C8655F68526923240918074D0147C03162E4A49200648C652803C6FD7509AE9AA799D6310D0BD42724E0635920186207000767CA5A8546B1755308C304B84FC93B069E265985B398D6B834698287FF829AA820F17A7F4226AB21F601EBD7175226BAB256D8888F009032566D6383D68457EA155A94301870D589C678ED304259E9D37B193BC2A7CCBCBEC51D69158C44073AEC9792630253318BC954DBF50D15028290DC2D309C7B7B02A6823744D463DA17749595CB77E6D16D20D1B4C3AAD89D320EBE5A672BB96D6CD5C1EFEC8B811200CBB062E473352540EDDEF8AF9499F8CDD1DC7C6873F0C7A6BCB7097560271F946849B7F373640BB69CA9B518AA380A6EB0A7275EE84E9C221AED88F5BFBAF43A3EDE8E6AA42558104FAF800E018441930376C6F6E751569971F47ADBCA5CA00C801988F317A18722A29298925EA154DBC9024E120524A2D41DC0F18FD8D909F6C50977404E201767078BA9A1F9E40A8B2BA9C01B7DA3A0B73A4C2A6B4F518BBEE3455D0AF2204DDC031C805C72CCB647940B1E6794D859AAEBCEA0DEB581D61B9248BD9697B5CB974A8176E8F910469CAE0AB4ED92D2AEE9F7EB50296DAF8057476305C1189D1D9840A0944F0447FB81E511420E67891B98FA6C257034D5A063437D379177CE8D3FA6EAF12E2DBB7EB8E498481612B1929617DA5FB45E4CDF893927D8BA842AA861D9C50471C6D0C6DF7E2BB26465A0EB6A3A709DE792AAFAAF922AA95DD5920B72B4B8856C6E632860B10F5CC08450003671AF388961872B466400ADB815BA81EA794945D19A100622A6CA0D41C4EA620C21DC125119E372418F04402D9FA7180F7BC89AFA54F8082244A42F46E5B5ABCE87B50A7D6FEBE8D7BBBAC92657CBDA1DB7C25572A4C1D0BAEA30447A865A2B1036B880037E2F4D26D453E9E913259779E9169B28A62EB809A5C744E04E260E1F2BBDA874F1AC674839DDB47B3148C5946DE0180148B7973D63C58193B17CD05D16E80CD7928C2A338363A23A81C0608C87505589B9DA1C617E7B70786B6754FBB30A5816810B9E126CFCC5AA49326E9D842973874B6359B5DB75610BA68A98C7B5E83F125A82522E13B83FB8F864E2A97B73B5D544A7415B6504A13939EAB1595D64FAF41FAB25A864A574DE524405E878339877886D2FC07FA0311508252413EDFA1158466667AFF78386DAF7CB4C9B850992F96E20525330599AB601D454688E294C8C3E");
        byte[] privK = Hex.decode("59044102F3CFBE1BE03C144102F7EF75FBEF83043F7CFC20C20BEEC007DE3F041FBF0BFF401041030C40040FAE7E103F7E100085FC013D1410C80C2F000810461C2F480BEE8017D17F07F1411BA24013C1BDF83DC407D17E07C13917F0F9044045FC40BD0FF07D07EF0003DFC1F3CFFD1FC03FEFC0B8FC6E7B0BBDBD0FE0BE17D14307EFFE0FBFC6F81FBFF43EC1F87041D42083EC3DC2F4407BF84EC4140FC403F037F3FEC013E0FEE02180082F83FBE07BFFE043F40EC6FFB1BF200007FFBFFA0FFF6FFBCE83EBFEBEFC0FFDF3F103FC6F3FF0500A18718308007D03F200E4213BF04FFD17D000F0017A17F180E04FFF07DEC2244048148E8704503EE06F86080243F81FFF03BF4003F07EF3DE02FBFFC00420C1F40FBDF0707E043FF5FFD0000430400C4F49F4207C142F80EC3E010BFF7C13F07FF85F7F17E07C17FF33FC4EC303FFBCFFEEC41830FF0831BDF45F05F06FC503B0C0F84E4013E100E7E1441450C2FBEEBC0C0FBEFC60BCFFEF3CFBDF4303EF800BF2BE0BF001F01F43F41FFE08517B001141E00144F7EF8007CEBDFFFF4213A0B9F8A0FE04103C17E0820BB1C30C30C00FFFFC00007D18017CFF90C3101E7E103040FC4FBE04213E07AF80FFEFC80FBFBD0810BCFB8FBC087FB8FFF1010C2E81002F3EF3BF01F07E41FBC07F2C0FB8F43F401C5D81FFCEBE07C07E0BF17EEBEE830C514003FF7EF3E08403D1FFFFE105F840C20BDF0607FFFEF46E7EFFF08000400DE830000F3F82EF9D82E84EFFF3CEC4E81E01002103102EFC080F3B0801041BAE42F7F040F83EC31010031BC0410FAFF9F0004010133A089FFEF7BE8317A0020FEF010052BA04107E100F821C2F41F44F4EF7B000F02E41F82F380830FE08A1F707FF82EC7F42E81004041103E8307B13D0FDFF8F830F9FC5FFCD7E040F410FFFB9F423750860C11C5FFA144EC0080F02DC0F420820450790020BCF80EFFFBCEC4FBFF4200AFC00C02060C004303EF81FFA104107E4117AF01F81202FC1E44143FFE206EB3E881BB13F13920403FF7A000144102E7FFC2143E7FF4AF3F13F07E181DC317E240F4500303F2DDDCF1E1513E3EF15E8DC1309E50AEE03EFDC17081706FD03E6ECE4F30EBD1909051906E90CE806EB0B19E719EFFBF10D0DF1DC0CF6F1F4F8FEFBE9F9550E2107FCDCCBDFE9F4F7EE1AF8142115F910002AF2F5FF141ADA220AECFE040CEF0B29EB201930F2D3E401E5DEEFF4DDEA17F1FE141217F81C36050109F8F61F02DD19F90310C7F40208E9052C3942F8FFF2CCF9FDF83CFA12DC091C0D02F00411F5281E40D7F92DBA11D73D04C10BFD13E617110AF3ED05F6CFE705E0F70E1FF80533FC120C002CE81FF52638190FE3FED6F0FBBB23E6F408EF32220B13DD27F007E5FA00D72614F0E302210707EC111E070E2A032DF91DE3FCE800F1F9F2F7FE170101180412CBD1E90019F2011522DAEAED13F8E5F425DCEF24E01CE614E7DCEC01F2F4F914F4010107ED26E2E9DF0BF5F007EA07FAFBC6D7E607FAFCFD270DFD0D17FC4EF0EE00071AECDE09F8F215E113F80209CCF308D7E6251ECE0EDFED0CC9F4050B2714F61BF703F0EBF104010DEBFBF21AFC1BF01823FEDEFAF7F807E3F3020AEB01FE19EEE8E90D00E5FAED1EFDF628E5F0E6F0FC13F4FB05FB0B09EA0A0E08EE13293212E90CE4FEF223F4FF030BEBED1B402ED2F6171102BC0CF9E9F335ED0C01FAF0FEFAE41DF0050A162C11171CD90BEE211218EDFAFA0F03F4171412F319D60B01FAEE1F2823F0D6EF12D6DFEAFBFC170DECDA06E7CED500031E");
        byte[] msg = Hex.decode("D81C4D8D734FCBFBEADE3D3F8A039FAA2A2C9957E835AD55B22E75BF57BB556AC8");
        byte[] s = Hex.decode("3933b3c07507e4201748494d832b6ee2a6c93bff9b0ee343b550d1f85a3d0de0d704c6d17842951309290765843d1e460d17a527d2bca405bd55bbc7da09a8c620be0af4a767d9db96b80f55e466676751eaaba7b93b86d71132daa0eb376782b9eee37519ce10fdd33fe9f29312c31d8736206d165cf4c528aa3ddc017845e1f0dd5b0a44ff961c42d874a95533e5b438982f524ca954d87533bfbe42c63ff2abc77a34c79db55a99171bbcb72c842a6530af2f753f0c34ac632f9f1e7949f0bf6c67665b27722a8857d626b6ff1a136d923a39f4069b7477ff946e5247a6627791d49b59edc9e2525a860e6e9828d18f64a9f17222e8166a02453859bbda0b8186d8c9928bb571e4146401d7430e225904673ad21ccac54c146c248a1dd69ab6491e901d6d71b152155be97de057f3916a3f1b4273308c29b2f4d9697167b90681b1583ed930a71e990467dea368134beceebd597f9bec922e816f1b0570d728f4ae0464c1f797657f87a4e52dcdcaeb9272662ea66d7c6cd8781b31af555ad93f5f65e75816cb8dc306bb67e592b5261baca7c509629ea2af8abb80cba89ee535b76dfd9ccbbe3bf48f2bc8aa34b26e1103291053f5cb8de3a45afa5a76df8b2122ed2c82fbcf2259290d41a14f86b12f35f5d49762b34cff13ee7e42edec70201d7f37c33316288fa3078e36e58108865c3cfe263d563692043decc62f3426f86061285b7b1b336f56ff41bb65e9cd6d9b92fd90f864aa1c923cb8c755f5cde1770d862595427149d7721aaab5d194aea9acdeca15be43cba6a62b5a33909e9fc4da1c5814fbd7cd6a2fa572e318b42c6c319140b86e66392580a11a2b431f44c1f9270e4f7b2490f3b325a9977a71a575915636635b9969dbd6d220b24c3d99cebbbd834b88222bd08c3abe124e80");
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("Falcon", "BCPQC");
        SecureRandom katRandom = new NISTSecureRandom(Hex.decode("061550234D158C5EC95595FE04EF7A25767F2E24CC2BC479D09D86DC9ABCFDE7056A8C266F9EF97ED08541DBD2E1FFA1"), null);

        kpg.initialize(FalconParameterSpec.falcon_512, katRandom);

        KeyPair kp = kpg.generateKeyPair();

        SubjectPublicKeyInfo pubInfo = SubjectPublicKeyInfo.getInstance(kp.getPublic().getEncoded());

        assertTrue(Arrays.areEqual(ASN1OctetString.getInstance(pubInfo.getPublicKeyData().getOctets()).getOctets(), pubK));

        PrivateKeyInfo privInfo = PrivateKeyInfo.getInstance(kp.getPrivate().getEncoded());

        assertTrue(Arrays.areEqual(ASN1OctetString.getInstance(privInfo.getPrivateKey().getOctets()).getOctets(), privK));

        Signature sig = Signature.getInstance("Falcon", "BCPQC");

        sig.initSign(kp.getPrivate(), katRandom);

        sig.update(msg, 0, msg.length);

        byte[] genS = sig.sign();

        assertTrue(Arrays.areEqual(s, genS));

        sig = Signature.getInstance("Falcon", "BCPQC");

        sig.initVerify(kp.getPublic());

        sig.update(msg, 0, msg.length);

        assertTrue(sig.verify(s));
    }

    private static class RiggedRandom
            extends SecureRandom
    {
        public void nextBytes(byte[] bytes)
        {
            for (int i = 0; i != bytes.length; i++)
            {
                bytes[i] = (byte)(i & 0xff);
            }
        }
    }

}
