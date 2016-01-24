//
//  BLETransmitter.m
//  BLEBeacon
//
//  Created by Abdul Al-Shawa on 2016-01-23.
//  Copyright © 2016 Abdul Al-Shawa. All rights reserved.
//

#import <CoreLocation/CoreLocation.h>
#import <CoreBluetooth/CoreBluetooth.h>

#import "BLETransmitter.h"


@interface BLETransmitter () <CBPeripheralManagerDelegate>

@property (nonatomic, readonly) NSString * _Nonnull regionID;

@end


@implementation BLETransmitter {
    CLBeaconRegion *beaconRegion;
    NSDictionary *beaconData;
    CBPeripheralManager *beaconPeripheralManager;
}

- (instancetype)initWithIdentifier:(NSUUID * _Nonnull)uuid
                             major:(NSUInteger)majorNumber
                             minor:(NSUInteger)minorNumber
                          regionID:(NSString * _Nonnull)regionID
{
    if ([[self class] isIdentifierValidForAdvertising:uuid])
    {
        if (self = [super init])
        {
            _uuid = uuid;
            _majorNumber = majorNumber;
            _minorNumber = minorNumber;
            _regionID = regionID;
            _isTransmitting = NO;
            
            beaconRegion = [[CLBeaconRegion alloc] initWithProximityUUID:self.uuid
                                                                   major:majorNumber
                                                                   minor:minorNumber
                                                              identifier:regionID];
            
            beaconData = [beaconRegion peripheralDataWithMeasuredPower:nil];
            beaconPeripheralManager = [[CBPeripheralManager alloc] initWithDelegate:self
                                                                              queue:nil
                                                                            options:nil];
        }
        
        return self;
    }
    
    return nil;
}

- (BOOL)updateTransmitterIdentifier:(NSUUID * _Nonnull)updatedUUID
                              major:(NSUInteger)majorNumber
                              minor:(NSUInteger)minorNumber
{
    if ([[self class] isIdentifierValidForAdvertising:updatedUUID])
    {
        _uuid = updatedUUID;
        _majorNumber = majorNumber;
        _minorNumber = minorNumber;
        
        [self turnOffTransmitter];
        
        beaconRegion = [[CLBeaconRegion alloc] initWithProximityUUID:self.uuid
                                                               major:majorNumber
                                                               minor:minorNumber
                                                          identifier:self.regionID];
        
        beaconData = [beaconRegion peripheralDataWithMeasuredPower:nil];
        
        return YES;
    }
    
    return NO;
}

- (BOOL)turnOffTransmitter
{
    if (beaconPeripheralManager.state == CBPeripheralManagerStatePoweredOn) {
        [beaconPeripheralManager stopAdvertising];
    }
    
    _isTransmitting = NO;
    
    return YES;
}

- (BOOL)turnOnTransmitter
{
    if (beaconPeripheralManager.state == CBPeripheralManagerStatePoweredOn) {
        [beaconPeripheralManager startAdvertising:beaconData];
        
    }
    
    _isTransmitting = YES;
    
    return YES;
}

#pragma mark - Utilities

+ (BOOL)isIdentifierValidForAdvertising:(NSUUID *)testID
{
    return YES;
}

#pragma mark - CoreBluetooth Delegate Methods

-(void)peripheralManagerDidUpdateState:(CBPeripheralManager*)peripheral
{
    if (peripheral.state == CBPeripheralManagerStatePoweredOn)
    {
        // Bluetooth is on
        NSLog(@"BLE Broadcasting...");
        
        if (beaconPeripheralManager.isAdvertising != _isTransmitting) {
            // Start broadcasting
            [beaconPeripheralManager startAdvertising:beaconData];
        }
    }
    else if (peripheral.state == CBPeripheralManagerStatePoweredOff)
    {
        // Update our status label
        NSLog(@"Stopped");
        
        // Bluetooth isn't on. Stop broadcasting
        [beaconPeripheralManager stopAdvertising];
    }
    else if (peripheral.state == CBPeripheralManagerStateUnsupported)
    {
        NSLog(@"Peripheral Manager state is Unsupported");
    }
}

@end
