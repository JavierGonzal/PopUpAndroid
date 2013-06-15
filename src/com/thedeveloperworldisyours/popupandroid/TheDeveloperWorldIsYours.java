package com.thedeveloperworldisyours.popupandroid;

    import android.app.Activity;
    import android.content.Context;
    import android.graphics.Point;
    import android.graphics.Rect;
    import android.graphics.drawable.BitmapDrawable;
    import android.os.Bundle;
    import android.view.Display;
    import android.view.Gravity;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.View.OnClickListener;
    import android.view.Window;
    import android.view.WindowManager;
    import android.widget.Button;
import android.widget.ImageButton;
    import android.widget.LinearLayout;
import android.widget.PopupWindow;

    public class TheDeveloperWorldIsYours extends Activity {
    	private Point p;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_the_developer_world_is_yours);
            Button popUpButton = (Button) findViewById(R.id.open);
            popUpButton.setOnClickListener(new OnClickListener() {

    			@Override
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				if (p !=null)
    				showPopup(TheDeveloperWorldIsYours.this, p);
    			}
    		});

        }
        @Override
        public void onWindowFocusChanged(boolean hasFocus) {
                int[] location = new int[2];
                Button button = (Button) findViewById(R.id.open);

                button.getLocationOnScreen(location);
                // Initialize the Point with x, and y positions
                p = new Point();
                p.x = location[0];
                p.y = location[1];

        }
     // The method that displays the popup.

        private void showPopup(final Activity context, Point p) {

                Rect rectgle= new Rect();
                Window window= getWindow();
                window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
                int StatusBarHeight= rectgle.top;
                int contentViewTop=
                    window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
                int TitleBarHeight= contentViewTop - StatusBarHeight;
                Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                int popupWidth = display.getWidth();
                int popupHeight = (display.getHeight()-StatusBarHeight);

                // Inflate the popup_layout.xml
                LinearLayout viewGroup = (LinearLayout) context
                                .findViewById(R.id.popupLinearLayout);
                LayoutInflater layoutInflater = (LayoutInflater) context
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View layout = layoutInflater.inflate(R.layout.activity_popup, viewGroup);

                // Creating the PopupWindow
                final PopupWindow popup = new PopupWindow(context);
                popup.setContentView(layout);
                popup.setWidth(popupWidth);
                popup.setHeight(popupHeight);
                popup.setFocusable(true);
                popup.setAnimationStyle(R.style.PopupWindowAnimation);

                // Some offset to align the popup a bit to the right, and a bit down,
                // relative to button's position.

                int OFFSET_X = 0;
                int OFFSET_Y = 0;
                // Clear the default translucent background
                popup.setBackgroundDrawable(new BitmapDrawable());
                // Displaying the popup at the specified location, + offsets.
                popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y
                                + OFFSET_Y);

                // Getting a reference to Close button, and close the popup when
                // clicked.
                Button close = (Button) layout.findViewById(R.id.close);
                close.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                popup.dismiss();
                        }
                });

        }

    }

