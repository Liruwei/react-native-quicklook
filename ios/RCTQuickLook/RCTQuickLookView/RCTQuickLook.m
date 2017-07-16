//
//  RCTQuickLookView.m
//  RCTQuickLookView
//
//  Created by RuweiLi on 2017/7/6.
//  Copyright © 2017年 Facebook. All rights reserved.
//

#import "RCTQuickLook.h"
#import "RCTQuickLookContentView.h"


@implementation RCTQuickLook

RCT_EXPORT_MODULE()

/** 文件地址 */
RCT_EXPORT_VIEW_PROPERTY(fileUrl, NSString)

- (UIView *)view {
  return [[RCTQuickLookContentView alloc] init];
}

@end
