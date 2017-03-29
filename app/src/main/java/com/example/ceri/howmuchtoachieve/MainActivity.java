package com.example.ceri.howmuchtoachieve;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;

public class MainActivity extends Activity {

    EditText edit;
    EditText edit2;
    ArrayList<EditText> editTextArrayList;
    ArrayList<Pair> Mark_Percentage_Pair;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        editTextArrayList = new ArrayList<EditText>();
        addView(null);
    }


    public void addView(View v){
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.field_add);

        LinearLayout h = new LinearLayout(this);

        ViewGroup.LayoutParams editText = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f);


        h.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        h.setOrientation(LinearLayout.HORIZONTAL);


        EditText edit4 = new EditText(this);
        edit4.setGravity(Gravity.CENTER_HORIZONTAL);
        edit4.setEms(10);
        edit4.setLayoutParams(editText);







        EditText edit43 = new EditText(this);
        edit43.setEms(10);
        edit43.setGravity(Gravity.CENTER_HORIZONTAL);
        edit43.setLayoutParams(editText);
        edit4.setHint("Mark out of 100");

        edit43.setHint("Percentage Worth");


        edit43.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.green), PorterDuff.Mode.SRC_ATOP);
        edit4.getBackground().mutate().setColorFilter(ContextCompat.getColor(this, R.color.green), PorterDuff.Mode.SRC_ATOP);

        edit4.setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        edit43.setRawInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);

        editTextArrayList.add(edit43);
        editTextArrayList.add(edit4);

        edit4.setTextColor(BLACK);
        edit43.setTextColor(BLACK);

        h.addView(edit4);
        h.addView(edit43);
        mainLayout.addView(h);
    }


    public void getText(View v){
        if(checkFields(editTextArrayList) == false){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("One or more fields are empty!").setTitle("ERROR!")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    });
            builder.show();
    }else{
            CalculateGrade();

        }

    }

    private void CalculateGrade() {
        EditText editText = (EditText) findViewById(R.id.editText);
        double current_percent = 0;
        double answer = 0;
        TextView text = (TextView)findViewById(R.id.textView3);

        double mark_temp = 0;
        double percent_temp = 0;
        double mark_added = 0;
        double overall_percent =0;
        double percentage_added = 0;
        for (int i = 0; i < (editTextArrayList.size()); i += 2) {

            mark_temp = Double.parseDouble(editTextArrayList.get(i + 1).getText().toString());

            mark_added += mark_temp;
            percent_temp = Double.parseDouble(editTextArrayList.get(i).getText().toString()) / 100;
            overall_percent += percent_temp*100;
            percentage_added += percent_temp;

            current_percent += mark_temp * percent_temp;
        }
        if(overall_percent > 100){
            displayOver();
        }else if (editText.getText().toString().equals("")) {
            text.setText("You current have " + current_percent + "% out of "+ overall_percent + "%\n You have " + (current_percent/overall_percent)*100 + "% out of the available marks");
        } else {
            double whatIwantToAchieve = Double.parseDouble(editText.getText().toString());
            answer = (100 / (100 - (percentage_added * 100)) * (whatIwantToAchieve - current_percent));
            editText.setLines(2);
            text.setText("Your need to achieve " + Double.toString(answer) + "%\n in your remaining assesments to achieve " + whatIwantToAchieve + "% overall");
        }

    }

    private void displayOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The current percentage exceeds 100%").setTitle("ERROR!")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        builder.show();
    }

    private Boolean checkFields(ArrayList<EditText> editTextArrayList) {
        for (int i = 0; i < editTextArrayList.size(); i++){
            if(editTextArrayList.get(i).getText().toString().equals("")){
                return false;

            }
        }
        return true;
    }


}



