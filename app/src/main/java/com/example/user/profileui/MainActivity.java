package com.example.user.profileui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    TextView getEmail;
    TextView getPhone;
    TextView sendWA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        getPhone = (TextView) findViewById(R.id.phone);
        getPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent savePhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+6282251567214"));
                startActivity(savePhone);
            }
        });

        getEmail = (TextView) findViewById(R.id.email);
        getEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEmail = new Intent(Intent.ACTION_SEND);
                sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"jakkirahman@gmail.com"});
                sendEmail.setType("message/rfc822");
                startActivity(sendEmail);
            }
        });

        sendWA = (TextView) findViewById(R.id.wa);
        sendWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendToWA = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/6282251567214"));
                startActivity(sendToWA);
            }
        });

    }

    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view) {
        closeDrawer(drawerLayout);
    }

    public void ClickAbout(View view) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.drawer_layout, new AboutFragment())
                .commit();
        closeDrawer(drawerLayout);
    }

    public void ClickExit(View view) {
        exitapp(this);
    }

    private static void exitapp(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("EXIT");
        builder.setMessage("Exit app?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void instagram(View view1) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/jackyrachman233"));
        startActivity(browserIntent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}