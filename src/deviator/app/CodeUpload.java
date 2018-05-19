package deviator.app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class CodeUpload {

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		String uploadsDir = "uploads/";

		String fileName = "aa/" + file.getOriginalFilename();
		Path filePath = Paths.get(fileName);
		final Path tmp = filePath.getParent();

		if (file.isEmpty()) {
			return "No File Content Being uploaded... Please verify";
		}

		String s1 = "<h1> Thank you! Code has been uploaded, Our Team would get back to You!!!</h1>";

		try {

			Files.createDirectories(filePath.getParent());
			final BufferedWriter w = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8,
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			w.write(new String(file.getBytes()));
			w.flush();
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
			s1 = "<h1> Issue while uploading, Try Again If issue persist please contact the us via mail !!!</h1>";
		}

		return s1;
	}

}
