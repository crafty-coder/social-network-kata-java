package org.craftycoder.socialkata.domain.actions;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ViewWallShould {


    @Test
    public void recover_an_empty_wall() {

        ViewWall viewWall = new ViewWall();

        List<String> result = viewWall.view("Bob");

        assertEquals(Collections.emptyList(), result);

    }


}