# NiceProgressBar
a nice progressbar for android
## step 1

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.flyou:NiceProgressBar:1.0.2'
	}

## step 2
add in you xml

      <com.xdja.progressbarlibrary.NiceProgressBar
            android:id="@+id/progressbar1"
            android:layout_width="150dp"
            android:layout_height="150dp"

            />

## step 3
     this.progressbar1 = (NiceProgressBar) findViewById(R.id.progressbar1);
     progressbar1.setTextMax(88).show();

### more

        progressbar2.setTextMax(33)
        .setTextColor(getResources().getColor(R.color.red))
        .setDefaultWheelColor(getResources().getColor(R.color.sandybrown))
        .setWheelColor(getResources().getColor(R.color.yellow))
        .show();

 setpercent type and Listener

	 progressbar3.setTextMax(100)
        .showWithPercent(true)
                .setProgressBarListener(new NiceProgressBar.ProgressBarListener() {
                    @Override
                    public void onProgressBarComplete() {
                        // TODO: do what you want
                    }

                    @Override
                    public void onProgressBarStart() {
                        // TODO: do what you want
                    }
                })
        .show();

    }


![](http://ww4.sinaimg.cn/large/a2f7c645jw1fcsh5ysarzg207n06xgo0.gif)

![](http://ww4.sinaimg.cn/large/a2f7c645jw1fcsh6d4yc7g207n06x77w.gif)

![](http://ww4.sinaimg.cn/large/a2f7c645jw1fcsh6kceecg207n06xgoj.gif)

**License**

NiceProgressBar is under the Apache2.0 license. See the [LICENSE](https://github.com/flyou/NiceProgressBar/blob/master/LICENSE) file for details.
