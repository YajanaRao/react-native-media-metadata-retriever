package com.rnmediametadataretriever;

import android.media.MediaMetadataRetriever;
import android.widget.Toast;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import java.util.HashMap;



public class RNMediaMetadataRetrieverModule extends ReactContextBaseJavaModule {
  
  private static final String NOT_FOUND_ERROR = "NOT_FOUND_ERROR";

  @Override
  public String getName() {
    return "RNMediaMetadataRetriever";
  }


  @ReactMethod
  public void getMetadata(String uri, Promise promise) {
     WritableMap map = Arguments.createMap();
     MediaMetadataRetriever mmr = new MediaMetadataRetriever();
    try{
      mmr.setDataSource(uri, new HashMap<String, String>());
      String songGenre = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
      String album =  mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
      String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
      String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
      String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

      map.putString("genre"  ,songGenre);
      map.putString("album",album);
      map.putString("duration",duration);
      map.putString("artist", artist);
      map.putString("title", title);
      promise.resolve(map);
    } catch(RuntimeException exp){
       promise.reject(NOT_FOUND_ERROR,exp);
    } finally {
      mmr.release();
    }
  }

  @ReactMethod
  public void getPicture(String uri, Promise promise){
    WritableMap map = Arguments.createMap();
    MediaMetadataRetriever mmr = new MediaMetadataRetriever();
    try{
      byte[] data = mmr.getEmbeddedPicture();
      if(data != null){
        String artcover = new String();
        map.putString("artcover", artcover);
      } else{
        map.putString("artcover", null);
      }
      promise.resolve(map);
    } catch(RuntimeException exp){
      promise.reject(NOT_FOUND_ERROR,exp);
    } finally {
      mmr.release();
    }
  }

  public RNMediaMetadataRetrieverModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }
}