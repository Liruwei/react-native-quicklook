//
//  RCTQuickLookContentView.m
//  RCTQuickLookView
//
//  Created by RuweiLi on 2017/7/6.
//  Copyright © 2017年 Facebook. All rights reserved.
//

#import "RCTQuickLookContentView.h"
#import <CommonCrypto/CommonCrypto.h>
#import <QuickLook/QuickLook.h>


@interface  RCTQuickLookContentView()
<
QLPreviewControllerDataSource,
QLPreviewControllerDelegate
>

@property (nonatomic, strong) QLPreviewController *previewController;
@property (nonatomic, strong) NSURL *previewItem;
@property (nonatomic, copy) NSString *fileUrl;
@property (strong, nonatomic) UIView *errorView;
@end

@implementation RCTQuickLookContentView

- (instancetype)init {
    if (self = [super init]) {
        _previewController = [[QLPreviewController alloc] init];
        _previewController.delegate = self;
        _previewController.dataSource = self;
    }
    return self;
}


- (void)layoutSubviews {
    [super layoutSubviews];
    if (self.previewController.view.superview&&
        !CGRectEqualToRect(self.bounds, self.previewController.view.frame)) {
        self.previewController.view.frame = self.bounds;
    }
    
    if (self.errorView.superview) {
        self.errorView.frame = self.bounds;
        UILabel *label = [self.errorView viewWithTag:123];
        [label sizeToFit];
        label.center = self.errorView.center;
    }
}

- (void)setFileUrl:(NSString *)fileUrl {
    _fileUrl = fileUrl;
    if ([[NSFileManager defaultManager] fileExistsAtPath:fileUrl]) {
        [self addSubview:self.previewController.view];
        [self.errorView removeFromSuperview];
        _previewItem = [NSURL fileURLWithPath:_fileUrl];
        [self.previewController reloadData];
    } else {
        [self.previewController.view removeFromSuperview];
        [self addSubview:self.errorView];
    }
}

- (UIView *)errorView {
    if (!_errorView) {
        _errorView = [[UIView alloc] init];
        UILabel *label = [[UILabel alloc] init];
        label.text = @"无法打开文件";
        label.tag = 123;
        label.font = [UIFont boldSystemFontOfSize:15];
        label.textColor = [UIColor blackColor];
        [label sizeToFit];
        [_errorView addSubview:label];
    }
    return _errorView;
}



#pragma mark - QLPreviewControllerDelegate
#pragma mark - QLPreviewControllerDataSource
- (NSInteger)numberOfPreviewItemsInPreviewController:(QLPreviewController *)controller {
    return 1;
}

- (id <QLPreviewItem>)previewController:(QLPreviewController *)controller previewItemAtIndex:(NSInteger)index {
    return self.previewItem;
}

@end
