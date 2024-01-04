package com.company;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // Шифруем с шифром

        String s = "Hi, world!";
        System.out.println("\n\n--------------script--------------\n\n");

        // Объявляем шифр
        Cipher cipher = Cipher.getInstance("RSA");

        // Создаём ключь
        //выбрали шифр
        KeyPairGenerator pairGenerator = KeyPairGenerator.getInstance("RSA");
        // сложность рандома
        SecureRandom secureRandom = new SecureRandom();
        pairGenerator.initialize(512, secureRandom);
        //генерируем и создаём 2 ключа
        KeyPair keyPair = pairGenerator.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key priveteKey = keyPair.getPrivate();

        //устанавливаем ключь в наш шифр
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // шифруем текст
        byte[] bytes = cipher.doFinal(s.getBytes());
        for (byte b: bytes){
            System.out.print(b+" | ");
        }
        System.out.println("\n");
        System.out.println("16 бит");
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes){
            builder.append(String.format("%02X" + " | ", b));
        }
        System.out.println(builder.toString());

        System.out.println("\n\n--------------decriptoin--------------\n\n");

        //расшифровываем текст
        Cipher decriptoin = Cipher.getInstance("RSA");
        decriptoin.init(Cipher.DECRYPT_MODE, priveteKey);
        byte[] descpBytes = decriptoin.doFinal(bytes);
        for (byte b: descpBytes){
            System.out.print((char) b);
        }
        System.out.println("\n\n--------------end--------------\n\n");
    }
}
