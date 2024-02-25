package secure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.IOException;

public class txtsecurior {
	public static void main(String[] args) {
		String folderPath = "C:\\";
		String passwordFilePath = "C:\\securios\\123.txt";

		if (checkPassword(passwordFilePath)) {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("1. 비밀번호 변경");
				System.out.println("2. 폴더 열기");
				System.out.println("3. 종료");
				System.out.print("선택: ");
				int choice = scanner.nextInt();
				scanner.nextLine(); // 개행 문자 제거

				switch (choice) {
				case 1:
					changePassword(passwordFilePath);
					break;
				case 2:
					openFolder(folderPath);
					break;
				case 3:
					try {
			            Process process = Runtime.getRuntime().exec("cmd");


			            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

			            writer.write("shutdown -s -t 15 \n");
			            writer.flush();

			            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));


			            String line;
			            while ((line = reader.readLine()) != null) {
			                System.out.println(line);
			            }

			            process.waitFor();

			            process.destroy();

			        } catch (IOException | InterruptedException e) {
			            e.printStackTrace();
			        }
				default:
					System.out.println("잘못된 선택입니다. 다시 선택하세요.");
					break;
				}
			}
		} else {
			System.out.println("비밀번호가 올바르지 않습니다.");
		}
	}

	private static boolean checkPassword(String passwordFilePath) {
		try {
			String storedPassword = Files.readString(new File(passwordFilePath).toPath(), StandardCharsets.UTF_8);
			Scanner scanner = new Scanner(System.in);
			System.out.print("비밀번호를 입력하세요: ");
			String enteredPassword = scanner.nextLine();
			return storedPassword.equals(enteredPassword);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void changePassword(String passwordFilePath) {
		try {
			FileWriter writer = new FileWriter(passwordFilePath);
			Scanner scanner = new Scanner(System.in);
			System.out.print("새로운 비밀번호를 입력하세요: ");
			String newPassword = scanner.nextLine();
			writer.write(newPassword);
			writer.close();
			System.out.println("비밀번호가 변경되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openFolder(String folderPath) {
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null && files.length > 0) {
				System.out.println("폴더 내의 파일 목록:");
				for (File file : files) {
					System.out.println(file.getName());
				}
			} else {
				System.out.println("폴더 내에 파일이 없습니다.");
			}
		} else {
			System.out.println("해당 경로는 폴더가 아닙니다.");
		}
	}
}
