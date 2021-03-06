package com.gvit.gims.attendance.Export;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.gvit.gims.attendance.Student;
import com.gvit.gims.attendance.dtos.ClassInformationDTO;

/**
 * @author Ajaykumar Vasireddy
 * @version 0.1
 * @since 2014
 */
public class ExportToExcel {

	private Context context;
	private ClassInformationDTO classInfoDTO;

	public ExportToExcel(ClassInformationDTO classSelection, Context context) {
		this.context = context;
		this.classInfoDTO = classSelection;
	}

	public Boolean exportDataToCSV(String outFileName) {

		final File DATABASE_DIRECTORY = new File(this.context.getFilesDir(),
				"AttendanceExport");
		Log.e("excel", "in exportDatabasecsv()");
		Boolean returnCode = false;

		String csvHeader = "";
		String csvValues = "";

		try {

			if (!DATABASE_DIRECTORY.exists()) {
				DATABASE_DIRECTORY.mkdirs();
			}
			Log.e("export fun:file name", outFileName);
			File outFile = new File(DATABASE_DIRECTORY, outFileName);
			if (!outFile.exists()) {
				FileWriter fileWriter = new FileWriter(outFile, true);
				Log.e("after FileWriter :file name", outFile.toString());
				BufferedWriter out = new BufferedWriter(fileWriter);
				csvHeader += "\"" + "Date" + "\",";
				csvHeader += "\"" + "Group" + "\",";
				csvHeader += "\"" + "Department" + "\",";
				csvHeader += "\"" + "Section" + "\",";
				csvHeader += "\"" + "Year" + "\",";
				csvHeader += "\"" + "Period" + "\",";
				csvHeader += "\"" + "Subject" + "\",";
				csvHeader += "\"" + "Regno" + "\",";
				csvHeader += "\"" + "FirstName" + "\",";
				csvHeader += "\"" + "LastName" + "\",";
				
				csvHeader += "\n";
				out.write(csvHeader);
				out.flush();
				fileWriter.close();
			}
			FileWriter fileWriter = new FileWriter(outFile, true);
			Log.e("after FileWriter :file name", outFile.toString());
			BufferedWriter out = new BufferedWriter(fileWriter);
			ArrayList<Student> absentees = classInfoDTO.getAbsentees();
			for (int i = 0; i < absentees.size(); i++) {
				Student student = absentees.get(i);
				csvValues += classInfoDTO.getAttendanceDate() + ",";
				csvValues += classInfoDTO.getGroup() + ",";
				csvValues += classInfoDTO.getDepartment() + ",";
				csvValues += classInfoDTO.getSection() + ",";
				csvValues += classInfoDTO.getYear() + ",";
				csvValues += classInfoDTO.getPeriod() + ",";
				csvValues += classInfoDTO.getSubject() + ",";
				csvValues += student.getRegno() + ",";
				csvValues += student.getFirstName() + ",\n";
			}
			out.write(csvValues);
			out.flush();
			fileWriter.close();
			returnCode = true;
		} catch (Exception e) {
			returnCode = false;
			Log.e("Exception", e.getMessage());
		}
		return returnCode;
	}
}
