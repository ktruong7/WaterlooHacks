//
//  BLETransmitter.h
//  BLEBeacon
//
//  Created by Abdul Al-Shawa on 2016-01-23.
//  Copyright © 2016 Abdul Al-Shawa. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface BLETransmitter : NSObject

@property (nonatomic, readonly) NSUUID * _Nonnull uuid;
@property (nonatomic, readonly) NSUInteger majorNumber;
@property (nonatomic, readonly) NSUInteger minorNumber;
@property (nonatomic, readonly) BOOL isTransmitting;

- (instancetype _Nonnull)initWithIdentifier:(NSUUID * _Nonnull)uuid
                                      major:(NSUInteger)majorNumber
                                      minor:(NSUInteger)minorNumber
                                   regionID:(NSString * _Nonnull)regionID;

- (BOOL)updateTransmitterIdentifier:(NSUUID * _Nonnull)updatedUUID
                              major:(NSUInteger)majorNumber
                              minor:(NSUInteger)minorNumber;
- (BOOL)turnOffTransmitter;
- (BOOL)turnOnTransmitter;

+ (BOOL)isIdentifierValidForAdvertising:(NSUUID * _Nonnull)testID;

@end
