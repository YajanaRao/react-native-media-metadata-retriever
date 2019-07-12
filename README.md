# react-native-media-metadata-retriever
React Native Media Metadata Retriever API for Android

`npm install https://github.com/YajanaRao/react-native-media-metadata-retriever.git --save`

#### Import Module:

`import RNMediaMetadataRetriever from 'react-native-media-metadata-retriever' `

#### Retrieve Metadata from A song

```
RNMediaMetadataRetriever.getMetadata(uri)
  .then((info) => {
      console.log(info)
  })
  .catch((error) => {
      console.log(error)
  })
```

#### Response :

```
{ 
  title: 'Give In To Me',
  artist: 'Michael Jackson',
  duration: '328829',
  album: 'Dangerous',
  genre: 'Hip Hop' 
}
```

#### Get Embeded picture :
```
RNMediaMetadataRetriever.getPicture(this.state.active.url)
  .then((response) => {
      if(response.artcover){
          let track = this.state.active;
          track.artcover = response.artcover;
          this.setState({
              active: track
          })
      }
  })
  .catch((error) => {
      console.log(error);
  })
  
  ```
  
  #### Response :
  `{ artcover: http://kannadasongs.cc/thumbs/42.png }`
