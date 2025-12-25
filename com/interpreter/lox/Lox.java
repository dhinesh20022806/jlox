package com.interpreter.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



public class Lox {

    static boolean hadError = false;
    public static void main(String args[]) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");

            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }
    
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));

        run(new String(bytes, Charset.defaultCharset()));

        if (hadError) {
            System.exit(64);
        }

    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        
        for (;;) {
           try{
            System.out.print("> ");
            String line = reader.readLine();

            if (line.equals(".exit();")) {
                line = null;
            }
            
            if (line == null) {
                System.out.println("Exit program");
                break;
            }

            run(line);
            hadError = false;
            
        } catch (Error e) {
            System.out.println("Exited");
           }
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        if (hadError) {
            System.exit(64);
        }

        for (Token token : tokens) {
            System.out.println(token);
        }

    }
    
    public static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

} 