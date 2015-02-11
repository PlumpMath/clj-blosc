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
    int blosc_compress(int clevel, int doshuffle, int typesize,
                      int nbytes, byte[] src, byte[] dest,
                      int destsize);
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
    System.out.println(clevel);
    return 10;
  }
}
