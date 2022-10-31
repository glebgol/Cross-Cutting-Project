package interfaces;

import java.util.ArrayList;

public interface IStream {
    byte[] bytes();
    ArrayList<String> lines();
}
