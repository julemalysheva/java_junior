package ru.geekbrains.chat.server;

import java.io.*;
import java.net.Socket;

public class ClientManage implements Runnable {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public ClientManage(Socket socket) {
        try {
            this.socket = socket;
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            ClientManageSingleton.getInstance().add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Server: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }


    /**
     * Закрытие соединения с клиентским сокетом, завершение работы всех потоков,
     * удаление клиентского сокета из коллекции
     * @param socket клиентский сокет
     * @param bufferedReader буфер для чтения данных
     * @param bufferedWriter буфер для отправки данных
     */
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            //Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            //Завершаем работу буфера на запись данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            //Завершаем работу клиентского сокета
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление клиента из коллекции
     */
    private void removeClient() {
        ClientManageSingleton.getInstance().remove(this);
        System.out.println(name + " покинул чат.");
    }

    @Override
    public void run() {
        String messageFromClient;

        //Цикл чтения данных от клиента
        while (socket.isConnected()) {
            try {
            messageFromClient = bufferedReader.readLine();
            broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    /**
     * Отправка сообщения всем слушателям
     * @param message сообщение
     */
    private void broadcastMessage(String message) {
        for (ClientManage clientManage: ClientManageSingleton.getInstance()) {
            try {
                //Отправим сообщение, если наименование клиента не совпадает с отправителем
                if (!clientManage.name.equals(name)) {
                    clientManage.bufferedWriter.write(message);
                    clientManage.bufferedWriter.newLine();
                    clientManage.bufferedWriter.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
}
