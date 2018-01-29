package presenter.com;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import presenter.com.app.buffers.MessageBuffer;
import presenter.com.app.core.Connect;
import presenter.com.messages.Next;
import presenter.com.messages.Prev;

public class Home extends AppCompatActivity {
    String m_Text = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void next(View v) {
        MessageBuffer.getInstance().addMessage(new Next());
        Log.d("string", "next");

    }

    public void prev(View v) {
        MessageBuffer.getInstance().addMessage(new Prev());
        Log.d("string", "prev");
    }

    public void conect(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(this);
        input.setText("192.168.0.101");
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Conectar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Connect c = new Connect(m_Text, 12345);
                c.execute();

                findViewById(R.id.conect).setEnabled(false);
                findViewById(R.id.conect).setVisibility(View.INVISIBLE);
                findViewById(R.id.button2).setEnabled(true);
                findViewById(R.id.prev).setEnabled(true);
                findViewById(R.id.button2).setVisibility(View.VISIBLE);
                findViewById(R.id.prev).setVisibility(View.VISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();


//        Connect c = new Connect("192.168.0.101",12345);
//        c.execute();
    }
}
