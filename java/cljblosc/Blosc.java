package cljblosc;

import com.sun.jna.Library; 
import com.sun.jna.Native;
import com.sun.jna.Platform; 
import java.nio.ByteBuffer;

public class Blosc {
  public interface BloscLib extends Library{
    BloscLib INSTANCE = (BloscLib) Native.loadLibrary("blosc", BloscLib.class);

    void blosc_init();
    void blosc_destroy();
    int blosc_compress_ctx(int clevel, int doshuffle, int typesize,
                      int nbytes, byte[] src, byte[] dest,
                      int destsize, String comp, 
                      int blocksize, int numthreads);
    int blosc_decompress_ctx(byte[] src, byte[] dest, int destsize, int numthreads);
  }

  public void blosc_init()
  {
    BloscLib.INSTANCE.blosc_init();
  }
  public void blosc_destroy()
  {
    BloscLib.INSTANCE.blosc_destroy();
  }
  public int blosc_compress(int clevel, int doshuffle, int typesize, int nbytes, byte[] src, byte[] dest, int destsize)
  {
    System.out.print("Compressing\nLevel: ");
    System.out.println(clevel);
    System.out.print("Shuffle: ");
    if (1 == doshuffle) 
      System.out.println("Yes");
    else
      System.out.println("No");
    System.out.print("Type Size: ");
    System.out.println(typesize);
    System.out.println("Starting");
    return BloscLib.INSTANCE.blosc_compress_ctx(clevel, doshuffle, typesize, nbytes, src, dest, destsize, "blosclz", 0, 4);
  }
  public int blosc_decompress(byte[] src, byte[] dest, int destsz, int numthreads)
  {
    System.out.println("Decompressing");
    System.out.println(src);
    System.out.println(src[0]);
    System.out.println(destsz);
    return BloscLib.INSTANCE.blosc_decompress_ctx(src, dest, destsz, numthreads);
  }
}
