package ru.geekbrains.chat.server;

import java.util.ArrayList;

public class ClientManageSingleton {

    private static ArrayList<ClientManage> clients;

    private ClientManageSingleton() {
    }

    public static ArrayList<ClientManage> getInstance() {
        if (clients == null) {
            clients = new ArrayList<ClientManage>();
        }
        return clients;
    }
}
