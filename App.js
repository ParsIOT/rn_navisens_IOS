import React, { Component } from 'react';
import {NativeEventEmitter, NativeModules,WebView, Button} from 'react-native';
// import MessageQueue from 'react-native/Libraries/BatchedBridge/MessageQueue.js';
// const spyFunction = (msg) => {
//   console.log(msg);
// };
// MessageQueue.spy(spyFunction);
import {
  Platform,
  StyleSheet,
  Text,
  TextInput,
  View,
  Dimensions,
  Image,
  Animated
} from 'react-native';


motionDNAstring = ""
//here is in set states so that it move center of map to a lat and long . onPress={()=>{this.motionManager.setLocationLatitudeLongitude(60.0000002,60.0000002)}}
// location_localLocation_y:  (motionDna.location_globalLocation_latitude+motionDna.location_localLocation_x*100).toFixed(3),
// location_localLocation_x: (motionDna.location_globalLocation_longitude+motionDna.location_localLocation_y*100).toFixed(3),



type Props = {};

export default class App extends Component<Props> {
    componentDidMount(){
      this.op()
      // setInterval(function(){console.warn(this.state.location_localLocation_x)},2000)
    }

    

    constructor() {
        super();
        this.state = {
          loading:true,
          OPACITY:new Animated.Value(1),
            motionDNAstring: "test",
            locationStatus: "UNKNOWN",
            location_localLocation_x: "0",
            location_localLocation_z: "0",
            location_localLocation_y: "0",
            location_globalLocation_latitude: "lat",
            location_globalLocation_longitude: "long",
            location_globalLocation_altitude: "alt",
            location_localHeading: "heading",
            motion_motionType: "nada",
            gpsLocation_globalLocation_latitude: "lat",
            gpsLocation_globalLocation_longitude: "long",
            gpsLocation_globalLocation_altitude: "alt",
            navidate: "0",
            errorCode: "NO ERROR CODE",
            errorString: "NO ERROR STRING"
        };
        this.motionManager = NativeModules.MotionDnaReactBridge
        this.motionManager.runMotionDna("Pe7DuPo01JreUAB5vlNAMUcKSfeJi4fgLhfF43ZbbZ51QzO5STqiXXt6LqDlKUpe",() => {
        this.motionManager.setLocationNavisens();
          // this.motionManager.setLocationGPSOnly();
          // this.motionManager.setBinaryFileLoggingEnabled(true)
          this.motionManager.setLocalHeadingOffsetInDegrees(180)

          this.motionManager.setExternalPositioningState("HIGH_ACCURACY")
          this.motionManager.setPowerMode("PERFORMANCE");
          this.motionManager.setBackpropagationEnabled(true);
          // this.motionManager.setBackpropagationBufferSize(100);
          this.motionManager.setCallbackUpdateRateInMs(100)


          this.motionManager.setNetworkUpdateRateInMs(200)
        });

        this.motionDnaEmitter = new NativeEventEmitter(this.motionManager);
        console.log("set emitter")

        

        this.subscription = this.motionDnaEmitter.addListener(
            'MotionDnaEvent',
            (motionDna) => {
              this.refs.webview.postMessage(((this.state.location_localLocation_y*100).toFixed(2)).toString()+","+((this.state.location_localLocation_x*100).toFixed(2)).toString()+","+(this.Convert2zeroto360((this.state.location_localHeading*(-1)+180).toFixed(2))).toString())              
              //console.warn("yes")
              // console.warn("" + motionDna.location_localLocation_x.toFixed(3)+ "," + motionDna.location_localLocation_y.toFixed(3))
                // console.log("parameter: " + motionDna.location_localHeading);
                this.setState({
                  // motionDNAstring: motionDna.MotionDnaString,
                //   locationStatus: motionDna.location_locationStatus,
                    location_localLocation_x: motionDna.location_localLocation_x.toFixed(3),
                    location_localLocation_y: motionDna.location_localLocation_y.toFixed(3),
                //     location_localLocation_z: motionDna.location_localLocation_z.toFixed(3),
                //     location_globalLocation_latitude: motionDna.location_globalLocation_latitude.toFixed(5),
                //     location_globalLocation_longitude: motionDna.location_globalLocation_longitude.toFixed(5),
                //     location_globalLocation_altitude: motionDna.location_globalLocation_altitude.toFixed(3),
                    location_localHeading: motionDna.location_localHeading.toFixed(3),
                //     motion_motionType: motionDna.motion_motionType,
                //     gpsLocation_globalLocation_latitude: motionDna.GPSLocation_globalLocation_latitude.toFixed(5),
                //     gpsLocation_globalLocation_longitude: motionDna.GPSLocation_globalLocation_longitude.toFixed(5),
                //     gpsLocation_globalLocation_altitude: motionDna.GPSLocation_globalLocation_altitude.toFixed(3),
                //     navidate: motionDna.timestamp.toString()
                        });
                        // console.log(this.state.navidate)
                // this.setState({[motionDna.target.id]:motionDna.target.value});
            });

          this.errorSubscription = this.motionDnaEmitter.addListener('MotionDnaErrorEvent',(error) => {
            this.setState({
              errorCode: error.errorCode,
              errorString: error.errorString 
            });
          });
        console.log("done initializing")
        
        // this.motionManager.setMotionDnaCallback((err, parameter) => 
    }

    Convert2zeroto360(h){
      while (true) {
          if (h < 0) {
              h += 360;
          } else if (h > 360) {
              h -= 360;
          } else {
              return h;
          }
      }
  }

  
  onMessage = (data) => {                                         //the function to recieve message from webview
      console.log(data.nativeEvent.data)
  }


  op(){
    setTimeout(()=>{
      Animated.timing(
        this.state.OPACITY,
        {
          toValue:0,
          duration:500
        }
      ).start(()=>{this.setState({loading:false})})
  
    },2500)
  }

  f_loading(){
    if(this.state.loading){
      return(
        <Animated.View style={{opacity:this.state.OPACITY,alignItems:'center', justifyContent:'center', position:'absolute', alignSelf:'center', backgroundColor:'white', width:Dimensions.get("window").width, height:Dimensions.get("window").height}}>
          <Image source={require('./static/leaflet/ParsiotAnimatedLarge.gif')} style={{width:200,height:200}}/>
        </Animated.View> 
        )
    }
    else{
      return
    }
  }
  
    
  render() {
    return (
      <View style={{alignItems:'center', justifyContent:'center', position:'absolute', alignSelf:'center'}}>
      <View style={{marginTop: 20, width:Dimensions.get("window").width, height:Dimensions.get("window").height}}>
      <WebView
                // url='./android/app/static/leaflet/map.html'
                // source={{ uri: 'file:///external/leaflet/map.html' }}
                // startInLoadingState={true}
                source={require('./static/leaflet/map.html')}
                ref="webview"
                onMessage={this.onMessage}
                // javaScriptEnabledAndroid={true}
                scalesPageToFit={false}
            />
            </View>

            {this.f_loading()}
      </View>
      
      // <View style={styles.container}>
      //   <Text style={styles.welcome}>
      //     Test!
      //   </Text>
      //   {/* <TextInput style={styles.instructions}
      //   value={this.state.motionDNAstring}
      //   /> */}
      //   <Text style={styles.instructions}>
        // {"X: " + this.state.location_localLocation_x + " Y: " + this.state.location_localLocation_y + " Z: " + this.state.location_localLocation_z}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {"NAVISENS LOCATION"}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {"Lat: " + this.state.location_globalLocation_latitude + " Long: " + this.state.location_globalLocation_longitude + " Alt: " + this.state.location_globalLocation_altitude}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {"GPS LOCATION"}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {"Lat: " + this.state.gpsLocation_globalLocation_latitude + " Long: " + this.state.gpsLocation_globalLocation_longitude + " Alt: " + this.state.gpsLocation_globalLocation_altitude}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {this.state.location_localHeading}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {this.state.motion_motionType}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {this.state.navidate}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {this.state.errorCode}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {this.state.errorString}
      //   </Text>
      //   <Text style={styles.instructions}>
      //   {this.state.locationStatus}
      //   </Text>
      //   <Text style={styles.instructions}>
      //     {instructions}
      //   </Text>
      // </View>
    );

    
    
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#dd6260',
    marginBottom: 5,
  },
});
