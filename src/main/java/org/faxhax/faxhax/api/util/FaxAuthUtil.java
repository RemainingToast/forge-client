package org.faxhax.faxhax.api.util;

import org.faxhax.faxhax.FaxHax;
import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;
import oshi.software.os.OperatingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FaxAuthUtil {

    private final List<String> LICENSES = new ArrayList<>();

    public FaxAuthUtil(){
        try {
            String URL = "https://pastebin.com/raw/x0x12Rr4";
            URL pastebin = new URL(URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(pastebin.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                LICENSES.add(inputLine);
            }
        } catch (IOException e) {
            FaxHax.printLog("Failed to read licenses!!");
        }
    }

    private String generateLicenseKey() {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        ComputerSystem computerSystem = systemInfo.getHardware().getComputerSystem();
        return operatingSystem.getManufacturer() + "#" + computerSystem.getSerialNumber() + "#" + computerSystem.getHardwareUUID();
    }

    public Boolean isLicensed(String hwid) {
        return LICENSES.contains(hwid);
    }

    public String getLicenseKey(){
        return generateLicenseKey();
    }
}
