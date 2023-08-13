package project;

import java.io.IOException;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class VoiceAssistant {

	public static void main(String[] args) throws IOException {
		
		Configuration config = new Configuration();
		
		config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		config.setDictionaryPath("file:/D:/Voice%20Assistant/src/resources/8224.dic");
		config.setLanguageModelPath("file:/D:/Voice%20Assistant/src/resources/8224.lm");
		
		LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(config);
		
		recognizer.startRecognition(true);
		SpeechResult speech;
		
		while((speech = recognizer.getResult()) != null) {
			
			String command = speech.getHypothesis();
			
			System.out.println("Input Command is : " + command);
			
			if(command.equalsIgnoreCase("Open Chrome")) {
			    openApplication("chrome.exe");
			}
			else if(command.equalsIgnoreCase("Close Chrome")) {
				closeApplication("chrome.exe");
			}
			if(command.equalsIgnoreCase("Open Notepad")) {
			    openApplication("notepad.exe");
			}
			else if(command.equalsIgnoreCase("Close Notepad")) {
				closeApplication("notepad.exe");
			}
			if(command.equalsIgnoreCase("Open Excel")) {
			    openApplication("EXCEL.EXE");
			}
			else if(command.equalsIgnoreCase("Close Excel")) {
				closeApplication("EXCEL.EXE");
			}
			if(command.equalsIgnoreCase("Open Power Point")) {
			    openApplication("POWERPNT.EXE");
			}
			else if(command.equalsIgnoreCase("Close Power Point")) {
				closeApplication("POWERPNT.EXE");
			}
			if(command.equalsIgnoreCase("Open Word")) {
			    openApplication("WINWORD.EXE");
			}
			else if(command.equalsIgnoreCase("Close Word")) {
				closeApplication("WINWORD.EXE");
			}
			if(command.equalsIgnoreCase("Open CMD")) {
			    openApplication("cmd.exe");
			}
			else if(command.equalsIgnoreCase("Close CMD")) {
				closeApplication("cmd.exe");
			}
		}
	}
	
	private static void openApplication(String applicationName) {
	    String os = System.getProperty("os.name").toLowerCase();
	    ProcessBuilder processBuilder = new ProcessBuilder();

	    if (os.contains("win")) {
	        processBuilder.command("cmd.exe", "/c", "start", applicationName);
	    }
	    else {
	        System.out.println("Unsupported operating system");
	        return;
	    }

	    try {
	        Process process = processBuilder.start();
	        int exitCode = process.waitFor();
	        System.out.println("Command exited with code: " + exitCode);
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}

	
	private static void closeApplication(String processName) {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (os.contains("win")) {
            processBuilder.command("taskkill", "/F", "/IM", processName);
        } 
        else {
            processBuilder.command("pkill", processName);
        }

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            System.out.println("Command exited with code: " + exitCode);
        } 
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
	
}
