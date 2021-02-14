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

    private List<String> licenses = new ArrayList<>();

    private String licensesURL = "https://gist.githubusercontent.com/RemainingToast/3e0db94180053f7f8612229f43e7109e/raw/226a8007ec6a1b31875e461949227e9eef3e6442/licenses";

    public FaxAuthUtil(){
        try {
            URL pastebin = new URL(licensesURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(pastebin.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                licenses.add(inputLine);
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
        return licenses.contains(hwid);
    }

    public String getLicenseKey(){
        return generateLicenseKey();
    }
}
