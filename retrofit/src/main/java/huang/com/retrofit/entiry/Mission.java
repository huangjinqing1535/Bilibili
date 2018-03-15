package huang.com.retrofit.entiry;

import java.io.Serializable;

/**
 * Created by huangjinqing on 2018/1/11.
 */

public class Mission implements Serializable{

    public String name;
    public double X;
    public double Y;
    public double Z;

    @Override
    public String toString() {
        return "Mission{" +
                "name='" + name + '\'' +
                ", X=" + X +
                ", Y=" + Y +
                ", Z=" + Z +
                '}';
    }
}
