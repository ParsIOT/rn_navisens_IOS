//
//  MotionDnaManager.h
//  reactNativeHelloworld
//
//  Created by James Grantham on 3/14/18.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MotionDnaSDK/MotionDnaSDK.h>

@protocol MotionDnaDelegate
-(void)receiveMotionDna:(MotionDna*)motionDna;
-(void)reportError:(ErrorCode)error WithMessage:(NSString*)message;
@end

@interface MotionDnaManager : MotionDnaSDK
@property id<MotionDnaDelegate> controller;
-(void)receiveMotionDna:(MotionDna*)motionDna;
-(void)reportError:(ErrorCode)error WithMessage:(NSString*)message;
@end
