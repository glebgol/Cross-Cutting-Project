package com.example.restapi.interfaces;

import com.example.restapi.model.FileInfo;

public interface IFileDAO {
    FileInfo create(FileInfo file);
}
