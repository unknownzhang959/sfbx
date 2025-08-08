package com.itheima.sfbx.framework.outinterface.service;

import com.itheima.sfbx.framework.outinterface.dto.TokenDTO;
import okhttp3.*;

import java.io.*;

public interface TokenService {


    TokenDTO getToken() throws IOException;
}
