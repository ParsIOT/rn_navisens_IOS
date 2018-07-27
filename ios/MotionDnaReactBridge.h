//
//  MotionDnaManager.h
//  reactNativeHelloworld
//
//  Created by James Grantham on 3/14/18.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import <MotionDnaSDK/MotionDna.h>
#import "MotionDnaManager.h"

@interface MotionDnaReactBridge : RCTEventEmitter<RCTBridgeModule,MotionDnaDelegate>
@property (strong, nonatomic) MotionDnaManager *motionDnaManager;
- (instancetype)init;
+ (BOOL)requiresMainQueueSetup;

-(void)receiveMotionDna:(MotionDna*)motionDna;
@end
