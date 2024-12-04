package org.bouncycastle.crypto.threshold;

public class ShamirSplitSecret
    implements SplitSecret
{
    private final ShamirSplitSecretShare[] secretShares;
    private final Polynomial poly;

    public ShamirSplitSecret(ShamirSecretSplitter.Algorithm algorithm, ShamirSecretSplitter.Mode mode, ShamirSplitSecretShare[] secretShares)
    {
        this.secretShares = secretShares;
        this.poly = Polynomial.newInstance(algorithm, mode);
    }

    ShamirSplitSecret(Polynomial poly, ShamirSplitSecretShare[] secretShares)
    {
        this.secretShares = secretShares;
        this.poly = poly;
    }

    public ShamirSplitSecretShare[] getSecretShare()
    {
        return secretShares;
    }

    @Override
    public byte[] recombine()
    {
        int n = secretShares.length;
        byte[] r = new byte[n];
        byte tmp;
        byte[] products = new byte[n - 1];
        byte[][] splits = new byte[n][secretShares[0].getSecretShare().length];
        for (int i = 0; i < n; i++)
        {
            splits[i] = secretShares[i].getSecretShare();
            tmp = 0;
            for (int j = 0; j < n; j++)
            {
                if (j != i)
                {
                    products[tmp++] = poly.gfDiv(secretShares[j].r, secretShares[i].r ^ secretShares[j].r);
                }

            }

            tmp = 1;
            for (byte p : products)
            {
                tmp = (byte)poly.gfMul(tmp & 0xff, p & 0xff);
            }
            r[i] = tmp;
        }

        return poly.gfVecMul(r, splits);
    }
}
