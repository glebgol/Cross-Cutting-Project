package com.glebgol.businesslogic.contracts.interfaces;

import java.util.List;

public interface IStream {
    List<String> lines();
    byte[] bytes();
}
