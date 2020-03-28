package br.com.helpcar.db;

import java.util.ArrayList;
import java.util.List;

import br.com.helpcar.model.Called;

public class CalledDAO {
    private static final List<Called> CALLEDS = new ArrayList<>();

    public List<Called> getCalleds() {
        return CALLEDS;
    }

    public void adiciona(Called... calleds) {
        for (Called called : calleds) {
            CALLEDS.add(called);
        }
    }
}
