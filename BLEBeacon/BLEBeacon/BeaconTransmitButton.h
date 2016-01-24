//
//  BeaconTransmitButton.h
//  BLEBeacon
//
//  Created by Abdul Al-Shawa on 2016-01-23.
//  Copyright © 2016 Abdul Al-Shawa. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>


@protocol BeaconTransmitButtonDelegate;


@interface BeaconTransmitButton : UIView

@property (nonatomic, readonly) BOOL isEnabled;
@property (nonatomic, weak) id<BeaconTransmitButtonDelegate> delegate;

- (void)setEnabled:(BOOL)isEnabled;

@end


@protocol BeaconTransmitButtonDelegate <NSObject>

-(void)didToggleBeacon:(BOOL)isOn;

@end
