//
//  BeaconTransmitButton.m
//  BLEBeacon
//
//  Created by Abdul Al-Shawa on 2016-01-23.
//  Copyright © 2016 Abdul Al-Shawa. All rights reserved.
//

#import "BeaconTransmitButton.h"
#import "PulsingHaloLayer.h"

@implementation BeaconTransmitButton {
    BOOL _isEnabled;
    PulsingHaloLayer *pulseLayer;
}

- (BOOL)isEnabled
{
    return _isEnabled;
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self  action:@selector(viewTapped:)];
    tap.numberOfTapsRequired = 1;
    [self addGestureRecognizer:tap];
    
    pulseLayer = [PulsingHaloLayer layer];
    
    CGRect pulseRect = pulseLayer.frame;
    pulseRect.size = self.layer.frame.size;
    pulseLayer.frame = pulseRect;
    
    pulseLayer.haloLayerNumber = 5;
    pulseLayer.radius = 180.f;
    pulseLayer.animationDuration = 7.f;
    
    [self.layer insertSublayer:pulseLayer atIndex:0];
}

- (void)drawRect:(CGRect)rect
{
    CGContextRef context = UIGraphicsGetCurrentContext();
    
    const CGFloat borderWidth = 10.f;
    const CGFloat squareDimen = 30.f;
    const CGPoint midPoint = CGPointMake(CGRectGetMidX(self.bounds), CGRectGetMidY(self.bounds));
    
    CGRect eclipseRect = CGRectMake(midPoint.x - squareDimen / 2, midPoint.y - squareDimen / 2, squareDimen, squareDimen);
    CGContextSetRGBStrokeColor(context, 0.f, 0.455f, 0.756f, 0.4f);
    CGContextSetRGBFillColor(context, 0.f, 0.455f, 0.756f, 1.f);
    CGContextSetLineWidth(context, borderWidth);
    CGContextFillEllipseInRect (context, eclipseRect);
    CGContextStrokeEllipseInRect(context, eclipseRect);
    CGContextFillPath(context);
}

- (void)viewTapped:(UIGestureRecognizer *)gr
{
    [self setEnabled:!_isEnabled];
    [self.delegate didToggleBeacon:_isEnabled];
    [self updateAnimationState];
}

- (void)setEnabled:(BOOL)isEnabled
{
    _isEnabled = isEnabled;
}

- (void)updateAnimationState
{
    if (_isEnabled) {
        [pulseLayer beginAnimation];
    } else {
        [pulseLayer completeAnimation];
    }
}

@end

