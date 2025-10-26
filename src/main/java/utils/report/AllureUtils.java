package utils.report;

import utils.FilesUtils;
import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtils {
    public static final String AllurePath = "test-outputs/allure-results";

    private AllureUtils() {
        super();
    }

    public static void attachLogsToAllure() {
        try {
            File logFile = FilesUtils.getLatestFile(LogsUtils.LOGS_PATH);
            if (!logFile.exists()) {
                LogsUtils.warn("Log file does not exist:" + LogsUtils.LOGS_PATH);
                return;
            }
            Allure.addAttachment("Logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtils.info("Logs attached to Allure report");
        } catch (Exception e) {
            LogsUtils.error("Failed to attach logs to allure report:" + e.getMessage());
        }
    }

    public static void attachScreenshotsToAllure(String screenshotName, String screenshotPath) {
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotPath)));
            LogsUtils.info("Screenshot attached to Allure report");
        } catch (Exception e) {
            LogsUtils.error("Failed to attach screenshot to allure report:" + e.getMessage());
        }
    }
}
