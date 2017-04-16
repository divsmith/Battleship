package edu.weberstate.cs3230.ui;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by parker on 4/16/17.
 */
class UIGameTest {
    @Test
    void  UIGameplay_Test()
    {
        UIGame test = new UIGame();

        String[] args = new String[1];
        args[0] = "UI/test/resources/UIInput.txt";
        test.main(args);
    }
}