package org.bouncycastle.pqc.crypto.util;

import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.internal.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCParameters;
import org.bouncycastle.pqc.crypto.mldsa.MLDSAParameters;
import org.bouncycastle.pqc.crypto.mlkem.MLKEMParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicParameters;
import org.bouncycastle.pqc.crypto.saber.SABERParameters;
import org.bouncycastle.pqc.crypto.slhdsa.SLHDSAParameters;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSKeyParameters;
import org.bouncycastle.util.Integers;

class Utils
{
    static final AlgorithmIdentifier AlgID_qTESLA_p_I = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_I);
    static final AlgorithmIdentifier AlgID_qTESLA_p_III = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_III);

    static final AlgorithmIdentifier SPHINCS_SHA3_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha3_256);
    static final AlgorithmIdentifier SPHINCS_SHA512_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256);


    static final Map categories = new HashMap();

    static final Map picnicOids = new HashMap();
    static final Map picnicParams = new HashMap();

    static final Map frodoOids = new HashMap();
    static final Map frodoParams = new HashMap();

    static final Map saberOids = new HashMap();
    static final Map saberParams = new HashMap();

    static final Map mcElieceOids = new HashMap();
    static final Map mcElieceParams = new HashMap();

    static final Map sikeOids = new HashMap();
    static final Map sikeParams = new HashMap();

    static final Map ntruOids = new HashMap();
    static final Map ntruParams = new HashMap();

    static final Map falconOids = new HashMap();
    static final Map falconParams = new HashMap();

    static final Map ntruprimeOids = new HashMap();
    static final Map ntruprimeParams = new HashMap();

    static final Map sntruprimeOids = new HashMap();
    static final Map sntruprimeParams = new HashMap();

    static final Map dilithiumOids = new HashMap();
    static final Map dilithiumParams = new HashMap();

    static final Map bikeOids = new HashMap();
    static final Map bikeParams = new HashMap();

    static final Map hqcOids = new HashMap();
    static final Map hqcParams = new HashMap();

    static final Map rainbowOids = new HashMap();
    static final Map rainbowParams = new HashMap();

    static final Map mlkemOids = new HashMap<ASN1ObjectIdentifier, MLKEMParameters>();
    static final Map mlkemParams = new HashMap<MLKEMParameters, ASN1ObjectIdentifier>();

    static final Map mldsaOids = new HashMap<ASN1ObjectIdentifier, MLDSAParameters>();
    static final Map mldsaParams = new HashMap<MLDSAParameters, ASN1ObjectIdentifier>();

    static final Map shldsaOids = new HashMap<ASN1ObjectIdentifier, SLHDSAParameters>();
    static final Map shldsaParams = new HashMap<SLHDSAParameters, ASN1ObjectIdentifier>();
    
    static
    {
        mcElieceOids.put(CMCEParameters.mceliece348864r3, BCObjectIdentifiers.mceliece348864_r3);
        mcElieceOids.put(CMCEParameters.mceliece348864fr3, BCObjectIdentifiers.mceliece348864f_r3);
        mcElieceOids.put(CMCEParameters.mceliece460896r3, BCObjectIdentifiers.mceliece460896_r3);
        mcElieceOids.put(CMCEParameters.mceliece460896fr3, BCObjectIdentifiers.mceliece460896f_r3);
        mcElieceOids.put(CMCEParameters.mceliece6688128r3, BCObjectIdentifiers.mceliece6688128_r3);
        mcElieceOids.put(CMCEParameters.mceliece6688128fr3, BCObjectIdentifiers.mceliece6688128f_r3);
        mcElieceOids.put(CMCEParameters.mceliece6960119r3, BCObjectIdentifiers.mceliece6960119_r3);
        mcElieceOids.put(CMCEParameters.mceliece6960119fr3, BCObjectIdentifiers.mceliece6960119f_r3);
        mcElieceOids.put(CMCEParameters.mceliece8192128r3, BCObjectIdentifiers.mceliece8192128_r3);
        mcElieceOids.put(CMCEParameters.mceliece8192128fr3, BCObjectIdentifiers.mceliece8192128f_r3);

        mcElieceParams.put(BCObjectIdentifiers.mceliece348864_r3, CMCEParameters.mceliece348864r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece348864f_r3, CMCEParameters.mceliece348864fr3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece460896_r3, CMCEParameters.mceliece460896r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece460896f_r3, CMCEParameters.mceliece460896fr3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece6688128_r3, CMCEParameters.mceliece6688128r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece6688128f_r3, CMCEParameters.mceliece6688128fr3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece6960119_r3, CMCEParameters.mceliece6960119r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece6960119f_r3, CMCEParameters.mceliece6960119fr3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece8192128_r3, CMCEParameters.mceliece8192128r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece8192128f_r3, CMCEParameters.mceliece8192128fr3);

        frodoOids.put(FrodoParameters.frodokem640aes, BCObjectIdentifiers.frodokem640aes);
        frodoOids.put(FrodoParameters.frodokem640shake, BCObjectIdentifiers.frodokem640shake);
        frodoOids.put(FrodoParameters.frodokem976aes, BCObjectIdentifiers.frodokem976aes);
        frodoOids.put(FrodoParameters.frodokem976shake, BCObjectIdentifiers.frodokem976shake);
        frodoOids.put(FrodoParameters.frodokem1344aes, BCObjectIdentifiers.frodokem1344aes);
        frodoOids.put(FrodoParameters.frodokem1344shake, BCObjectIdentifiers.frodokem1344shake);

        frodoParams.put(BCObjectIdentifiers.frodokem640aes, FrodoParameters.frodokem640aes);
        frodoParams.put(BCObjectIdentifiers.frodokem640shake, FrodoParameters.frodokem640shake);
        frodoParams.put(BCObjectIdentifiers.frodokem976aes, FrodoParameters.frodokem976aes);
        frodoParams.put(BCObjectIdentifiers.frodokem976shake, FrodoParameters.frodokem976shake);
        frodoParams.put(BCObjectIdentifiers.frodokem1344aes, FrodoParameters.frodokem1344aes);
        frodoParams.put(BCObjectIdentifiers.frodokem1344shake, FrodoParameters.frodokem1344shake);

        saberOids.put(SABERParameters.lightsaberkem128r3, BCObjectIdentifiers.lightsaberkem128r3);
        saberOids.put(SABERParameters.saberkem128r3, BCObjectIdentifiers.saberkem128r3);
        saberOids.put(SABERParameters.firesaberkem128r3, BCObjectIdentifiers.firesaberkem128r3);
        saberOids.put(SABERParameters.lightsaberkem192r3, BCObjectIdentifiers.lightsaberkem192r3);
        saberOids.put(SABERParameters.saberkem192r3, BCObjectIdentifiers.saberkem192r3);
        saberOids.put(SABERParameters.firesaberkem192r3, BCObjectIdentifiers.firesaberkem192r3);
        saberOids.put(SABERParameters.lightsaberkem256r3, BCObjectIdentifiers.lightsaberkem256r3);
        saberOids.put(SABERParameters.saberkem256r3, BCObjectIdentifiers.saberkem256r3);
        saberOids.put(SABERParameters.firesaberkem256r3, BCObjectIdentifiers.firesaberkem256r3);
        saberOids.put(SABERParameters.ulightsaberkemr3, BCObjectIdentifiers.ulightsaberkemr3);
        saberOids.put(SABERParameters.usaberkemr3, BCObjectIdentifiers.usaberkemr3);
        saberOids.put(SABERParameters.ufiresaberkemr3, BCObjectIdentifiers.ufiresaberkemr3);
        saberOids.put(SABERParameters.lightsaberkem90sr3, BCObjectIdentifiers.lightsaberkem90sr3);
        saberOids.put(SABERParameters.saberkem90sr3, BCObjectIdentifiers.saberkem90sr3);
        saberOids.put(SABERParameters.firesaberkem90sr3, BCObjectIdentifiers.firesaberkem90sr3);
        saberOids.put(SABERParameters.ulightsaberkem90sr3, BCObjectIdentifiers.ulightsaberkem90sr3);
        saberOids.put(SABERParameters.usaberkem90sr3, BCObjectIdentifiers.usaberkem90sr3);
        saberOids.put(SABERParameters.ufiresaberkem90sr3, BCObjectIdentifiers.ufiresaberkem90sr3);

        saberParams.put(BCObjectIdentifiers.lightsaberkem128r3, SABERParameters.lightsaberkem128r3);
        saberParams.put(BCObjectIdentifiers.saberkem128r3, SABERParameters.saberkem128r3);
        saberParams.put(BCObjectIdentifiers.firesaberkem128r3, SABERParameters.firesaberkem128r3);
        saberParams.put(BCObjectIdentifiers.lightsaberkem192r3, SABERParameters.lightsaberkem192r3);
        saberParams.put(BCObjectIdentifiers.saberkem192r3, SABERParameters.saberkem192r3);
        saberParams.put(BCObjectIdentifiers.firesaberkem192r3, SABERParameters.firesaberkem192r3);
        saberParams.put(BCObjectIdentifiers.lightsaberkem256r3, SABERParameters.lightsaberkem256r3);
        saberParams.put(BCObjectIdentifiers.saberkem256r3, SABERParameters.saberkem256r3);
        saberParams.put(BCObjectIdentifiers.firesaberkem256r3, SABERParameters.firesaberkem256r3);
        saberParams.put(BCObjectIdentifiers.ulightsaberkemr3, SABERParameters.ulightsaberkemr3);
        saberParams.put(BCObjectIdentifiers.usaberkemr3, SABERParameters.usaberkemr3);
        saberParams.put(BCObjectIdentifiers.ufiresaberkemr3, SABERParameters.ufiresaberkemr3);
        saberParams.put(BCObjectIdentifiers.lightsaberkem90sr3, SABERParameters.lightsaberkem90sr3);
        saberParams.put(BCObjectIdentifiers.saberkem90sr3, SABERParameters.saberkem90sr3);
        saberParams.put(BCObjectIdentifiers.firesaberkem90sr3, SABERParameters.firesaberkem90sr3);
        saberParams.put(BCObjectIdentifiers.ulightsaberkem90sr3, SABERParameters.ulightsaberkem90sr3);
        saberParams.put(BCObjectIdentifiers.usaberkem90sr3, SABERParameters.usaberkem90sr3);
        saberParams.put(BCObjectIdentifiers.ufiresaberkem90sr3, SABERParameters.ufiresaberkem90sr3);

        picnicOids.put(PicnicParameters.picnicl1fs, BCObjectIdentifiers.picnicl1fs);
        picnicOids.put(PicnicParameters.picnicl1ur, BCObjectIdentifiers.picnicl1ur);
        picnicOids.put(PicnicParameters.picnicl3fs, BCObjectIdentifiers.picnicl3fs);
        picnicOids.put(PicnicParameters.picnicl3ur, BCObjectIdentifiers.picnicl3ur);
        picnicOids.put(PicnicParameters.picnicl5fs, BCObjectIdentifiers.picnicl5fs);
        picnicOids.put(PicnicParameters.picnicl5ur, BCObjectIdentifiers.picnicl5ur);
        picnicOids.put(PicnicParameters.picnic3l1, BCObjectIdentifiers.picnic3l1);
        picnicOids.put(PicnicParameters.picnic3l3, BCObjectIdentifiers.picnic3l3);
        picnicOids.put(PicnicParameters.picnic3l5, BCObjectIdentifiers.picnic3l5);
        picnicOids.put(PicnicParameters.picnicl1full, BCObjectIdentifiers.picnicl1full);
        picnicOids.put(PicnicParameters.picnicl3full, BCObjectIdentifiers.picnicl3full);
        picnicOids.put(PicnicParameters.picnicl5full, BCObjectIdentifiers.picnicl5full);

        picnicParams.put(BCObjectIdentifiers.picnicl1fs, PicnicParameters.picnicl1fs);
        picnicParams.put(BCObjectIdentifiers.picnicl1ur, PicnicParameters.picnicl1ur);
        picnicParams.put(BCObjectIdentifiers.picnicl3fs, PicnicParameters.picnicl3fs);
        picnicParams.put(BCObjectIdentifiers.picnicl3ur, PicnicParameters.picnicl3ur);
        picnicParams.put(BCObjectIdentifiers.picnicl5fs, PicnicParameters.picnicl5fs);
        picnicParams.put(BCObjectIdentifiers.picnicl5ur, PicnicParameters.picnicl5ur);
        picnicParams.put(BCObjectIdentifiers.picnic3l1, PicnicParameters.picnic3l1);
        picnicParams.put(BCObjectIdentifiers.picnic3l3, PicnicParameters.picnic3l3);
        picnicParams.put(BCObjectIdentifiers.picnic3l5, PicnicParameters.picnic3l5);
        picnicParams.put(BCObjectIdentifiers.picnicl1full, PicnicParameters.picnicl1full);
        picnicParams.put(BCObjectIdentifiers.picnicl3full, PicnicParameters.picnicl3full);
        picnicParams.put(BCObjectIdentifiers.picnicl5full, PicnicParameters.picnicl5full);

        falconOids.put(FalconParameters.falcon_512, BCObjectIdentifiers.falcon_512);
        falconOids.put(FalconParameters.falcon_1024, BCObjectIdentifiers.falcon_1024);

        falconParams.put(BCObjectIdentifiers.falcon_512, FalconParameters.falcon_512);
        falconParams.put(BCObjectIdentifiers.falcon_1024, FalconParameters.falcon_1024);

        mlkemOids.put(MLKEMParameters.ml_kem_512, NISTObjectIdentifiers.id_alg_ml_kem_512);
        mlkemOids.put(MLKEMParameters.ml_kem_768, NISTObjectIdentifiers.id_alg_ml_kem_768);
        mlkemOids.put(MLKEMParameters.ml_kem_1024,NISTObjectIdentifiers.id_alg_ml_kem_1024);

        mlkemParams.put(NISTObjectIdentifiers.id_alg_ml_kem_512, MLKEMParameters.ml_kem_512);
        mlkemParams.put(NISTObjectIdentifiers.id_alg_ml_kem_768, MLKEMParameters.ml_kem_768);
        mlkemParams.put(NISTObjectIdentifiers.id_alg_ml_kem_1024, MLKEMParameters.ml_kem_1024);

        mldsaOids.put(MLDSAParameters.ml_dsa_44, NISTObjectIdentifiers.id_ml_dsa_44);
        mldsaOids.put(MLDSAParameters.ml_dsa_65, NISTObjectIdentifiers.id_ml_dsa_65);
        mldsaOids.put(MLDSAParameters.ml_dsa_87, NISTObjectIdentifiers.id_ml_dsa_87);
        mldsaOids.put(MLDSAParameters.ml_dsa_44_with_sha512, NISTObjectIdentifiers.id_hash_ml_dsa_44_with_sha512);
        mldsaOids.put(MLDSAParameters.ml_dsa_65_with_sha512, NISTObjectIdentifiers.id_hash_ml_dsa_65_with_sha512);
        mldsaOids.put(MLDSAParameters.ml_dsa_87_with_sha512, NISTObjectIdentifiers.id_hash_ml_dsa_87_with_sha512);

        mldsaParams.put(NISTObjectIdentifiers.id_ml_dsa_44, MLDSAParameters.ml_dsa_44);
        mldsaParams.put(NISTObjectIdentifiers.id_ml_dsa_65, MLDSAParameters.ml_dsa_65);
        mldsaParams.put(NISTObjectIdentifiers.id_ml_dsa_87, MLDSAParameters.ml_dsa_87);
        mldsaParams.put(NISTObjectIdentifiers.id_hash_ml_dsa_44_with_sha512, MLDSAParameters.ml_dsa_44_with_sha512);
        mldsaParams.put(NISTObjectIdentifiers.id_hash_ml_dsa_65_with_sha512, MLDSAParameters.ml_dsa_65_with_sha512);
        mldsaParams.put(NISTObjectIdentifiers.id_hash_ml_dsa_87_with_sha512, MLDSAParameters.ml_dsa_87_with_sha512);

        dilithiumOids.put(DilithiumParameters.dilithium2, BCObjectIdentifiers.dilithium2);
        dilithiumOids.put(DilithiumParameters.dilithium3, BCObjectIdentifiers.dilithium3);
        dilithiumOids.put(DilithiumParameters.dilithium5, BCObjectIdentifiers.dilithium5);

        dilithiumParams.put(BCObjectIdentifiers.dilithium2, DilithiumParameters.dilithium2);
        dilithiumParams.put(BCObjectIdentifiers.dilithium3, DilithiumParameters.dilithium3);
        dilithiumParams.put(BCObjectIdentifiers.dilithium5, DilithiumParameters.dilithium5);

        bikeParams.put(BCObjectIdentifiers.bike128, BIKEParameters.bike128);
        bikeParams.put(BCObjectIdentifiers.bike192, BIKEParameters.bike192);
        bikeParams.put(BCObjectIdentifiers.bike256, BIKEParameters.bike256);

        bikeOids.put(BIKEParameters.bike128, BCObjectIdentifiers.bike128);
        bikeOids.put(BIKEParameters.bike192, BCObjectIdentifiers.bike192);
        bikeOids.put(BIKEParameters.bike256, BCObjectIdentifiers.bike256);

        hqcParams.put(BCObjectIdentifiers.hqc128, HQCParameters.hqc128);
        hqcParams.put(BCObjectIdentifiers.hqc192, HQCParameters.hqc192);
        hqcParams.put(BCObjectIdentifiers.hqc256, HQCParameters.hqc256);

        hqcOids.put(HQCParameters.hqc128, BCObjectIdentifiers.hqc128);
        hqcOids.put(HQCParameters.hqc192, BCObjectIdentifiers.hqc192);
        hqcOids.put(HQCParameters.hqc256, BCObjectIdentifiers.hqc256);

        shldsaOids.put(SLHDSAParameters.sha2_128s, NISTObjectIdentifiers.id_slh_dsa_sha2_128s);
        shldsaOids.put(SLHDSAParameters.sha2_128f, NISTObjectIdentifiers.id_slh_dsa_sha2_128f);
        shldsaOids.put(SLHDSAParameters.sha2_192s, NISTObjectIdentifiers.id_slh_dsa_sha2_192s);
        shldsaOids.put(SLHDSAParameters.sha2_192f, NISTObjectIdentifiers.id_slh_dsa_sha2_192f);
        shldsaOids.put(SLHDSAParameters.sha2_256s, NISTObjectIdentifiers.id_slh_dsa_sha2_256s);
        shldsaOids.put(SLHDSAParameters.sha2_256f, NISTObjectIdentifiers.id_slh_dsa_sha2_256f);
        shldsaOids.put(SLHDSAParameters.shake_128s, NISTObjectIdentifiers.id_slh_dsa_shake_128s);
        shldsaOids.put(SLHDSAParameters.shake_128f, NISTObjectIdentifiers.id_slh_dsa_shake_128f);
        shldsaOids.put(SLHDSAParameters.shake_192s, NISTObjectIdentifiers.id_slh_dsa_shake_192s);
        shldsaOids.put(SLHDSAParameters.shake_192f, NISTObjectIdentifiers.id_slh_dsa_shake_192f);
        shldsaOids.put(SLHDSAParameters.shake_256s, NISTObjectIdentifiers.id_slh_dsa_shake_256s);
        shldsaOids.put(SLHDSAParameters.shake_256f, NISTObjectIdentifiers.id_slh_dsa_shake_256f);

        shldsaOids.put(SLHDSAParameters.sha2_128s_with_sha256, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128s_with_sha256);
        shldsaOids.put(SLHDSAParameters.sha2_128f_with_sha256, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128f_with_sha256);
        shldsaOids.put(SLHDSAParameters.sha2_192s_with_sha512, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192s_with_sha512);
        shldsaOids.put(SLHDSAParameters.sha2_192f_with_sha512, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192f_with_sha512);
        shldsaOids.put(SLHDSAParameters.sha2_256s_with_sha512, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256s_with_sha512);
        shldsaOids.put(SLHDSAParameters.sha2_256f_with_sha512, NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256f_with_sha512);
        shldsaOids.put(SLHDSAParameters.shake_128s_with_shake128, NISTObjectIdentifiers.id_hash_slh_dsa_shake_128s_with_shake128);
        shldsaOids.put(SLHDSAParameters.shake_128f_with_shake128, NISTObjectIdentifiers.id_hash_slh_dsa_shake_128f_with_shake128);
        shldsaOids.put(SLHDSAParameters.shake_192s_with_shake256, NISTObjectIdentifiers.id_hash_slh_dsa_shake_192s_with_shake256);
        shldsaOids.put(SLHDSAParameters.shake_192f_with_shake256, NISTObjectIdentifiers.id_hash_slh_dsa_shake_192f_with_shake256);
        shldsaOids.put(SLHDSAParameters.shake_256s_with_shake256, NISTObjectIdentifiers.id_hash_slh_dsa_shake_256s_with_shake256);
        shldsaOids.put(SLHDSAParameters.shake_256f_with_shake256, NISTObjectIdentifiers.id_hash_slh_dsa_shake_256f_with_shake256);

        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_128s, SLHDSAParameters.sha2_128s);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_128f, SLHDSAParameters.sha2_128f);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_192s, SLHDSAParameters.sha2_192s);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_192f, SLHDSAParameters.sha2_192f);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_256s, SLHDSAParameters.sha2_256s);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_sha2_256f, SLHDSAParameters.sha2_256f);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_128s, SLHDSAParameters.shake_128s);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_128f, SLHDSAParameters.shake_128f);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_192s, SLHDSAParameters.shake_192s);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_192f, SLHDSAParameters.shake_192f);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_256s, SLHDSAParameters.shake_256s);
        shldsaParams.put(NISTObjectIdentifiers.id_slh_dsa_shake_256f, SLHDSAParameters.shake_256f);

        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128s_with_sha256, SLHDSAParameters.sha2_128s_with_sha256);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_128f_with_sha256, SLHDSAParameters.sha2_128f_with_sha256);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192s_with_sha512, SLHDSAParameters.sha2_192s_with_sha512);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_192f_with_sha512, SLHDSAParameters.sha2_192f_with_sha512);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256s_with_sha512, SLHDSAParameters.sha2_256s_with_sha512);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_sha2_256f_with_sha512, SLHDSAParameters.sha2_256f_with_sha512);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_128s_with_shake128, SLHDSAParameters.shake_128s_with_shake128);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_128f_with_shake128, SLHDSAParameters.shake_128f_with_shake128);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_192s_with_shake256, SLHDSAParameters.shake_192s_with_shake256);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_192f_with_shake256, SLHDSAParameters.shake_192f_with_shake256);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_256s_with_shake256, SLHDSAParameters.shake_256s_with_shake256);
        shldsaParams.put(NISTObjectIdentifiers.id_hash_slh_dsa_shake_256f_with_shake256, SLHDSAParameters.shake_256f_with_shake256);
    }

    static ASN1ObjectIdentifier slhdsaOidLookup(SLHDSAParameters params)
    {
        return (ASN1ObjectIdentifier)shldsaOids.get(params);
    }

    static SLHDSAParameters slhdsaParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (SLHDSAParameters)shldsaParams.get(oid);
    }
    
    static AlgorithmIdentifier sphincs256LookupTreeAlgID(String treeDigest)
    {
        if (treeDigest.equals(SPHINCSKeyParameters.SHA3_256))
        {
            return SPHINCS_SHA3_256;
        }
        else if (treeDigest.equals(SPHINCSKeyParameters.SHA512_256))
        {
            return SPHINCS_SHA512_256;
        }
        else
        {
            throw new IllegalArgumentException("unknown tree digest: " + treeDigest);
        }
    }

    static String sphincs256LookupTreeAlgName(SPHINCS256KeyParams keyParams)
    {
        AlgorithmIdentifier treeDigest = keyParams.getTreeDigest();

        if (treeDigest.getAlgorithm().equals(SPHINCS_SHA3_256.getAlgorithm()))
        {
            return SPHINCSKeyParameters.SHA3_256;
        }
        else if (treeDigest.getAlgorithm().equals(SPHINCS_SHA512_256.getAlgorithm()))
        {
            return SPHINCSKeyParameters.SHA512_256;
        }
        else
        {
            throw new IllegalArgumentException("unknown tree digest: " + treeDigest.getAlgorithm());
        }
    }

    static Digest getDigest(ASN1ObjectIdentifier oid)
    {
        if (oid.equals(NISTObjectIdentifiers.id_sha256))
        {
            return new SHA256Digest();
        }
        if (oid.equals(NISTObjectIdentifiers.id_sha512))
        {
            return new SHA512Digest();
        }
        if (oid.equals(NISTObjectIdentifiers.id_shake128))
        {
            return new SHAKEDigest(128);
        }
        if (oid.equals(NISTObjectIdentifiers.id_shake256))
        {
            return new SHAKEDigest(256);
        }

        throw new IllegalArgumentException("unrecognized digest OID: " + oid);
    }

    public static AlgorithmIdentifier getAlgorithmIdentifier(String digestName)
    {
        if (digestName.equals("SHA-1"))
        {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        }
        if (digestName.equals("SHA-224"))
        {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224);
        }
        if (digestName.equals("SHA-256"))
        {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
        }
        if (digestName.equals("SHA-384"))
        {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384);
        }
        if (digestName.equals("SHA-512"))
        {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
        }

        throw new IllegalArgumentException("unrecognised digest algorithm: " + digestName);
    }

    public static String getDigestName(ASN1ObjectIdentifier digestOid)
    {
        if (digestOid.equals(OIWObjectIdentifiers.idSHA1))
        {
            return "SHA-1";
        }
        if (digestOid.equals(NISTObjectIdentifiers.id_sha224))
        {
            return "SHA-224";
        }
        if (digestOid.equals(NISTObjectIdentifiers.id_sha256))
        {
            return "SHA-256";
        }
        if (digestOid.equals(NISTObjectIdentifiers.id_sha384))
        {
            return "SHA-384";
        }
        if (digestOid.equals(NISTObjectIdentifiers.id_sha512))
        {
            return "SHA-512";
        }

        throw new IllegalArgumentException("unrecognised digest algorithm: " + digestOid);
    }

    static ASN1ObjectIdentifier mcElieceOidLookup(CMCEParameters params)
    {
        return (ASN1ObjectIdentifier)mcElieceOids.get(params);
    }

    static CMCEParameters mcElieceParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (CMCEParameters)mcElieceParams.get(oid);
    }

    static ASN1ObjectIdentifier frodoOidLookup(FrodoParameters params)
    {
        return (ASN1ObjectIdentifier)frodoOids.get(params);
    }

    static FrodoParameters frodoParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (FrodoParameters)frodoParams.get(oid);
    }

    static ASN1ObjectIdentifier saberOidLookup(SABERParameters params)
    {
        return (ASN1ObjectIdentifier)saberOids.get(params);
    }

    static SABERParameters saberParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (SABERParameters)saberParams.get(oid);
    }

    static ASN1ObjectIdentifier picnicOidLookup(PicnicParameters params)
    {
        return (ASN1ObjectIdentifier)picnicOids.get(params);
    }

    static PicnicParameters picnicParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (PicnicParameters)picnicParams.get(oid);
    }

    static ASN1ObjectIdentifier falconOidLookup(FalconParameters params)
    {
        return (ASN1ObjectIdentifier)falconOids.get(params);
    }

    static FalconParameters falconParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (FalconParameters)falconParams.get(oid);
    }


    static ASN1ObjectIdentifier mlkemOidLookup(MLKEMParameters params)
    {
        return (ASN1ObjectIdentifier)mlkemOids.get(params);
    }

    static MLKEMParameters mlkemParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (MLKEMParameters)mlkemParams.get(oid);
    }

    static ASN1ObjectIdentifier mldsaOidLookup(MLDSAParameters params)
    {
        return (ASN1ObjectIdentifier)mldsaOids.get(params);
    }

    static MLDSAParameters mldsaParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (MLDSAParameters)mldsaParams.get(oid);
    }

    static ASN1ObjectIdentifier dilithiumOidLookup(DilithiumParameters params)
    {
        return (ASN1ObjectIdentifier)dilithiumOids.get(params);
    }

    static DilithiumParameters dilithiumParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (DilithiumParameters)dilithiumParams.get(oid);
    }

    static ASN1ObjectIdentifier bikeOidLookup(BIKEParameters params)
    {
        return (ASN1ObjectIdentifier)bikeOids.get(params);
    }

    static BIKEParameters bikeParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (BIKEParameters)bikeParams.get(oid);
    }

    static ASN1ObjectIdentifier hqcOidLookup(HQCParameters params)
    {
        return (ASN1ObjectIdentifier)hqcOids.get(params);
    }

    static HQCParameters hqcParamsLookup(ASN1ObjectIdentifier oid)
    {
        return (HQCParameters)hqcParams.get(oid);
    }
}
