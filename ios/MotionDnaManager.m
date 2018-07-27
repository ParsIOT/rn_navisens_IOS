//
//  MotionDnaManager.m
//  reactNativeHelloworld
//
//  Created by James Grantham on 3/14/18.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import "MotionDnaManager.h"

@implementation MotionDnaManager

-(void)receiveMotionDna:(MotionDna* )motionDna
{
  dispatch_async(dispatch_get_main_queue(), ^{
    
    @try {
      if ([[motionDna getID] containsString:[self getDeviceID]])
        [_controller receiveMotionDna:motionDna];
    }@catch (NSException *exception) {
      NSLog(@"failed to call getID on motionDna: %@",exception);
    }@finally {
      
    }
    
    
  });
}

-(void)reportError:(ErrorCode)error WithMessage:(NSString*)message
{
  dispatch_async(dispatch_get_main_queue(), ^{
    [_controller reportError:error WithMessage:message];
  });
}

@end
