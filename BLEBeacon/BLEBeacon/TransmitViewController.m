//
//  ViewController.m
//  BLEBeacon
//
//  Created by Abdul Al-Shawa on 2016-01-23.
//  Copyright © 2016 Abdul Al-Shawa. All rights reserved.
//

#import <CoreLocation/CoreLocation.h>
#import <CoreBluetooth/CoreBluetooth.h>

#import "TransmitViewController.h"
#import "BeaconTransmitButton.h"


#define ALT_BEACON_SERVICE @"8C422626-0C6E-4B86-8EC7-9147B233D97E"
#define ALT_BEACON_CHARACTERISTIC @"A05F9DF4-9D54-4600-9224-983B75B9D154"


@interface TransmitViewController () <UITextFieldDelegate, BeaconTransmitButtonDelegate>

@property (nonatomic, strong) BLETransmitter *transmitter;

@property (weak, nonatomic) IBOutlet UITextField *identifierTextField;
@property (weak, nonatomic) IBOutlet UITextField *majorIDTextField;
@property (weak, nonatomic) IBOutlet UITextField *minorIDTextField;
@property (weak, nonatomic) IBOutlet BeaconTransmitButton *beaconTransmitBtn;

@end

@implementation TransmitViewController {

}

- (void)loadView
{
    [super loadView];
    
    NSLog(@"Initializing...");
    
    NSUUID *defaultUUID = [[NSUUID alloc] initWithUUIDString:@"A77A1B68-49A7-4DBF-914C-760D07FBB8AA"];
    _transmitter = [[BLETransmitter alloc] initWithIdentifier:defaultUUID major:1 minor:12 regionID:@"com.waterloohacks.testregion"];
    
    self.identifierTextField.delegate = self;
    self.majorIDTextField.delegate = self;
    self.minorIDTextField.delegate = self;
    self.beaconTransmitBtn.delegate = self;
    
    // Default Values
    self.identifierTextField.text = self.transmitter.uuid.UUIDString;
    self.majorIDTextField.text = [NSString stringWithFormat: @"%zd", self.transmitter.majorNumber];
    self.minorIDTextField.text = [NSString stringWithFormat: @"%zd", self.transmitter.minorNumber];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    // Background gradient
    CGColorRef bkWhiteColor = [[UIColor whiteColor] CGColor];
    CGColorRef bkGrayColor = [[UIColor lightGrayColor] CGColor];
    
    CAGradientLayer *gradient = [CAGradientLayer layer];
    gradient.frame = self.view.bounds;
    gradient.colors = [NSArray arrayWithObjects:(__bridge id)bkWhiteColor, (__bridge id)bkWhiteColor, (__bridge id)bkWhiteColor, (__bridge id)bkGrayColor, nil];
    [self.view.layer insertSublayer:gradient atIndex:0];
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
}

#pragma mark - UI Actions

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    [textField resignFirstResponder];
    return NO;
}

- (void)textFieldDidEndEditing:(UITextField *)textField
{
    if (self.identifierTextField == textField) {
        [textField resignFirstResponder];
    } else if (self.majorIDTextField == textField) {
        [textField resignFirstResponder];
    } else if (self.minorIDTextField == textField) {
        [textField resignFirstResponder];
    }
    
    [self.transmitter updateTransmitterIdentifier:[[NSUUID alloc] initWithUUIDString:self.identifierTextField.text ] major:[self.majorIDTextField.text integerValue] minor:[self.minorIDTextField.text integerValue]];
    
//    [self.beaconTransmitBtn setEnabled:NO];
}

-(void)didToggleBeacon:(BOOL)isOn
{
    [self.view endEditing:YES];
    
    if (isOn) {
        [self.transmitter turnOnTransmitter];
    } else {
        [self.transmitter turnOffTransmitter];
    }
}

@end
