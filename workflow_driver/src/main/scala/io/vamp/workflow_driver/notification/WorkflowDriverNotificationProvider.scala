package io.vamp.workflow_driver.notification

import io.vamp.common.notification.{ DefaultPackageMessageResolverProvider, LoggingNotificationProvider }

trait WorkflowDriverNotificationProvider extends LoggingNotificationProvider with DefaultPackageMessageResolverProvider