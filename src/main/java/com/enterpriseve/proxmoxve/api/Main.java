package com.enterpriseve.proxmoxve.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static com.enterpriseve.proxmoxve.api.Secret.PASSWORD;
import static javax.swing.UIManager.get;

public class Main {

    public static void main(String[] args) throws JSONException {
        Client client = new Client("srv-px1.insa-toulouse.fr",8006);

        client.login("piszyna", PASSWORD,"Ldap-INSA");


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
}
