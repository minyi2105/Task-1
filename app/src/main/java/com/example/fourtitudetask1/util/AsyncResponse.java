package com.example.fourtitudetask1.util;

import com.example.fourtitudetask1.model.Dummy;

import java.util.List;

public interface AsyncResponse {
    void processFinish(Dummy dummy);
    void processFinish(List<Dummy> dummies);
    void populatedFinish();
}
