import { AppRegistry } from 'react-native';
import App from './App';
import Snoopy from 'rn-snoopy'
import bars from 'rn-snoopy/stream/bars'
import filter from 'rn-snoopy/stream/filter'
import buffer from 'rn-snoopy/stream/buffer'
import EventEmitter from 'react-native/Libraries/vendor/emitter/EventEmitter';
const emitter = new EventEmitter()
const events = Snoopy.stream(emitter)
filter({ type: Snoopy.TO_JS }, true)(events).subscribe()
AppRegistry.registerComponent('reactNativeHelloAgainWorld', () => App);
