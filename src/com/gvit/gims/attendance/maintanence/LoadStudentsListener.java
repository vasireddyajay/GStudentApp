/**
 * 
 */
package com.gvit.gims.attendance.maintanence;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

import com.Wsdl2Code.WebServices.Attendance.Attendance;
import com.Wsdl2Code.WebServices.Attendance.IWsdl2CodeEvents;
import com.Wsdl2Code.WebServices.Users.Student;
import com.Wsdl2Code.WebServices.Users.VectorStudent;
import com.gvit.gims.attendance.R;
import com.gvit.gims.attendance.login.LoginDBContentProvider;

/**
 * @author admin
 *
 */
public class LoadStudentsListener implements OnClickListener, IWsdl2CodeEvents {

	private ContentResolver contentResolver;
	private ProgressBar progressBar;

	public LoadStudentsListener(ContentResolver contentResolver,
			ProgressBar stuProgBar) {
		this.contentResolver = contentResolver;
		this.progressBar = stuProgBar;
	}

	@Override
	public void Wsdl2CodeStartedRequest() {
		progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void Wsdl2CodeFinished(String methodName, Object Data) {
		VectorStudent studentList = (VectorStudent) Data;
		for (Student stud : studentList) {
			ContentValues rowValue = new ContentValues();
			rowValue.put("YEAR", stud.currentYear);
			rowValue.put("BRANCH", stud.branch);
			rowValue.put("SECTION", stud.section);
			rowValue.put("REGNO", stud.regdNumber);
			rowValue.put("NAME", stud.name);
			getContentResolver().insert(
					LoginDBContentProvider.STUDENT_CONTENT_URI, rowValue);
		}
		progressBar.setVisibility(View.GONE);

	}

	private ContentResolver getContentResolver() {
		return contentResolver;
	}

	@Override
	public void Wsdl2CodeFinishedWithException(Exception ex) {

	}

	@Override
	public void Wsdl2CodeEndedRequest() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.studentsButton:
			getContentResolver().delete(
					LoginDBContentProvider.STUDENT_CONTENT_URI, null, null);
			Attendance attendanceService = new Attendance(this);
			try {
				attendanceService.getStudentsAsync();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}
