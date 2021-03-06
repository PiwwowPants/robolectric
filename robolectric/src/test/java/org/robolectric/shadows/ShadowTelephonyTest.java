package org.robolectric.shadows;

import static com.google.common.truth.Truth.assertThat;

import android.content.Context;
import android.os.Build.VERSION_CODES;
import android.provider.Telephony.Sms;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowTelephony.ShadowSms;

/** Unit tests for {@link ShadowTelephony}. */
@RunWith(RobolectricTestRunner.class)
@Config(minSdk = VERSION_CODES.KITKAT)
public class ShadowTelephonyTest {
  private static final String TEST_PACKAGE_NAME = "test.package.name";

  private Context context;

  @Before
  public void setUp() {
    context = RuntimeEnvironment.application;
  }

  @Test
  public void shadowSms_getDefaultSmsPackage() {
    ShadowSms.setDefaultSmsPackage(TEST_PACKAGE_NAME);

    assertThat(Sms.getDefaultSmsPackage(context)).isEqualTo(TEST_PACKAGE_NAME);
  }

  @Test
  public void shadowSms_getDefaultSmsPackage_returnsNull_whenNoSmsPackageIsSet() {
    // Make sure #reset is doing its job
    ShadowSms.setDefaultSmsPackage(TEST_PACKAGE_NAME);
    ShadowSms.reset();

    assertThat(Sms.getDefaultSmsPackage(context)).isNull();
  }
}
