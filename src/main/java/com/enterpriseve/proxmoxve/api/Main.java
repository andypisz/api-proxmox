package com.enterpriseve.proxmoxve.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static String USERNAME;
    public static String PASSWORD;

    public static void main(String[] args) throws JSONException {
        Client client = new Client("srv-px1.insa-toulouse.fr",8006);

        new Main().askUserForHisLogin();
        client.login(USERNAME, PASSWORD,"Ldap-INSA");


        System.out.println("Information about the server srv-px5:");
        System.out.println("====================");
        System.out.println();

        //Get server status of srv-px5
        JSONObject serverStatus = client.getNodes().get("srv-px5").getStatus().getRest().getResponse().getJSONObject("data");

        //Get CPU percentage for the server srv-px5 from server status
        double cpuPercentage = Double.parseDouble(serverStatus.getString("cpu"))*100;
        System.out.println("cpu percentage: "+cpuPercentage);


        //Get disk usage for the server srv-px5 from server status
        JSONObject rootfsInfo = serverStatus.getJSONObject("rootfs");
        double usedSpace = Double.parseDouble(rootfsInfo.getString("used"));
        double totalSpace = Double.parseDouble(rootfsInfo.getString("total"));
        double diskUsagePercentage = usedSpace/totalSpace*100;
        System.out.println("disk usage percentage: "+diskUsagePercentage);

        //Get disk usage for the server srv-px5 from server status
        JSONObject memoryInfo = serverStatus.getJSONObject("memory");
        double memoryUsedSpace = Double.parseDouble(memoryInfo.getString("used"));
        double memoryTotalSpace = Double.parseDouble(memoryInfo.getString("total"));
        double ramUsagePercentage = memoryUsedSpace/memoryTotalSpace*100;
        System.out.println("ram usage percentage: "+ramUsagePercentage);
        System.out.println();
    }




    //ask the user for his usernale and password
    public void askUserForHisLogin(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your username: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            USERNAME = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter your password: ");
        try {
            PASSWORD = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();

        //Print some stuff to hide the password
        System.out.println("");
        for (int i = 0;i<50;i++){
            System.out.println("************");
        }
        System.out.println("");
    }
}
