package com.rnmediametadataretriever;

import android.media.MediaMetadataRetriever;
import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

public class RNMediaMetadataRetrieverModule extends ReactContextBaseJavaModule {

  private static final int METADATA_KEY_ALBUM = 1;
  private static final int METADATA_KEY_ALBUMARTIST = 13;
  private static final int METADATA_KEY_ARTIST = 2;
  private static final int METADATA_KEY_AUTHOR = 3;
  private static final int METADATA_KEY_DURATION = 9;
  private static final int METADATA_KEY_GENRE = 6;
  private static final int METADATA_KEY_NUM_TRACKS = 10;
  private static final int METADATA_KEY_TITLE = 7;
  
  @Override
  public String getName() {
    return "RNMediaMetadataRetriever";
  }



  @ReactMethod
  public void show(String uri) {
    try{
      MediaMetadataRetriever mmr = new MediaMetadataRetriever();
      mmr.setDataSource(uri);
      String songGenre = mmr.extractMetadata(mmr.METADATA_KEY_GENRE);
      Toast.makeText(getReactApplicationContext(), songGenre, Toast.LENGTH_LONG).show();
    } catch(RuntimeException exp){
       Toast.makeText(getReactApplicationContext(), "error", Toast.LENGTH_LONG).show();
    }
  }

  public RNMediaMetadataRetrieverModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }
}