package com.gvit.gims.attendance;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.gvit.gims.attendance.Export.LoadCSVToDB;
import com.gvit.gims.attendance.dtos.ClassInformationDTO;

/**
 * @author Ajaykumar Vasireddy
 * @version 0.1
 * @since 2014
 */
public class ClassSelection extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_class_selection);
		turnOffCalenderViewForDatePicker();
		registerListenerOnStudentButton();
	}

	private void registerListenerOnStudentButton() {
		View loadStundButton = findViewById(R.id.loadStudentsButton);
		loadStundButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ClassSelection.this, Students.class);

				// 3. or you can add data to a bundle
				Bundle extras = new Bundle();
				Spinner groupSpin = (Spinner) findViewById(R.id.groupSpinner);
				String group = (String) groupSpin.getSelectedItem();

				Spinner departSpin = (Spinner) findViewById(R.id.departmentSpinner);
				String department = (String) departSpin.getSelectedItem();

				Spinner yearSpin = (Spinner) findViewById(R.id.yearSpinner);
				String year = (String) yearSpin.getSelectedItem();

				Spinner sectionSpin = (Spinner) findViewById(R.id.sectionSpinner);
				String section = (String) sectionSpin.getSelectedItem();

				Spinner periodSpin = (Spinner) findViewById(R.id.periodSpin);
				String period = (String) periodSpin.getSelectedItem();

				Spinner subjectSpin = (Spinner) findViewById(R.id.subjectSpin);
				String subject = (String) subjectSpin.getSelectedItem();

				DatePicker attenDatPick = (DatePicker) findViewById(R.id.attendanceDate);
				String attendanceDate = new StringBuilder()
						.append(attenDatPick.getDayOfMonth()).append("/")
						.append(attenDatPick.getMonth() + 1).append("/")
						.append(attenDatPick.getYear()).toString();

				StringBuilder calculatedString = new StringBuilder(group);
				calculatedString.append(department).append(year)
						.append(section);

				extras.putString("studentListSelection",
						calculatedString.toString());

				ClassInformationDTO classDTO = new ClassInformationDTO();
				classDTO.setDepartment(department);
				classDTO.setGroup(group);
				classDTO.setPeriod(period);
				classDTO.setSection(section);
				classDTO.setSubject(subject);
				classDTO.setYear(year);
				classDTO.setAttendanceDate(attendanceDate);
				extras.putSerializable("classSelectionDTO", classDTO);

				// 4. add bundle to intent
				intent.putExtras(extras);

				startActivity(intent);
			}
		});
	}

	private void turnOffCalenderViewForDatePicker() {
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		if (currentapiVersion >= 11) {
			try {
				View datePicker = findViewById(R.id.attendanceDate);
				Method m = datePicker.getClass().getMethod(
						"setCalendarViewShown", boolean.class);
				m.invoke(datePicker, false);
			} catch (Exception e) {
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		actionForMenuItemSelection(item);
		return super.onOptionsItemSelected(item);
	}

	private void actionForMenuItemSelection(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.export:
			Toast.makeText(this, "Exporting to ExcelSheet", Toast.LENGTH_SHORT)
					.show();
			String path = getBaseContext().getFilesDir().getAbsolutePath()
					+ "/AttendanceExport/" + "TodaysDate.csv";
			new LoadCSVToDB(getBaseContext(), path);
			break;
		}
	}
}
