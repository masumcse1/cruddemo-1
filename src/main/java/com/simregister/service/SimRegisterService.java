package com.simregister.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.simregister.model.Sim;
import com.simregister.validator.BeanValidator;

@Service
public class SimRegisterService {

	public void simRegistraion(List<Sim> listOfSim) throws IOException {

		Set<Sim> simsets = new HashSet<>();

		for (Sim simNo : listOfSim) {

			if (simsets.add(simNo) == false)
				System.out.println("duplicate Msidsn is  -------> " + simNo.getMsisdn());
		}
		
		

		for (Sim sim : simsets) {

			boolean isValidatedSim = BeanValidator.checkValidator(sim);

			if (isValidatedSim) {

				System.out.println("Registration success------------->" + sim.getMsisdn());

				// create new file
				createFileforValidatedSim(sim);

				// send wellcome
				sendWellcomeMsg(sim);

			}
		}

	}

	private void sendWellcomeMsg(Sim validateSim) {

		String smsbodyforMale = "Dear Mr " + validateSim.getName() + "your phone no has been registerd successfully";

		String smsbodyforFeMale = "Dear Ms " + validateSim.getName() + "your phone no has been registerd successfully";

		if (validateSim.getGender().equalsIgnoreCase("M")) {

			System.out.println(smsbodyforMale);

		} else if (validateSim.getGender().equalsIgnoreCase("F")) {
			System.out.println(smsbodyforFeMale);
		}
	}

	private void createFileforValidatedSim(Sim validateSim) {

		String fileName = validateSim.getMsisdn() + ".txt";

		try {
			File file = new File("C:\\msidsnfile\\filestoreforRegister\\" + fileName);

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String formattedDate = validateSim.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MMM-yy"));

			bw.write(validateSim.getMsisdn() + "," + validateSim.getAddress() + validateSim.getIdNumber()
					+ validateSim.getGender());
			bw.write("," + formattedDate);
			bw.write("," + validateSim.getName() + "," + validateSim.getSimType());

			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
