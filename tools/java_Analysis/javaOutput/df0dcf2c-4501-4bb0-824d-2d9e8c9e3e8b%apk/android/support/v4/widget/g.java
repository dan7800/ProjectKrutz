package android.support.v4.widget;

import android.view.View;

final class g extends aa
{
  private final int b;
  private z c;
  private final Runnable d = new g.1(this);

  public g(DrawerLayout paramDrawerLayout, int paramInt)
  {
    this.b = paramInt;
  }

  private void c()
  {
    int i = 3;
    if (this.b == i)
      i = 5;
    View localView = this.a.a(i);
    if (localView != null)
      this.a.d(localView);
  }

  public final void a()
  {
    this.a.removeCallbacks(this.d);
  }

  public final void a(int paramInt)
  {
    DrawerLayout localDrawerLayout = this.a;
    localDrawerLayout.a(paramInt, this.c.c());
  }

  public final void a(int paramInt1, int paramInt2)
  {
    if ((paramInt1 & 0x1) == 1);
    for (View localView = this.a.a(3); ; localView = this.a.a(5))
    {
      if ((localView != null) && (this.a.a(localView) == 0))
        this.c.a(localView, paramInt2);
      return;
    }
  }

  public final void a(z paramz)
  {
    this.c = paramz;
  }

  public final void a(View paramView, float paramFloat)
  {
    float f = DrawerLayout.b(paramView);
    int i = paramView.getWidth();
    int j;
    if (this.a.a(paramView, 3))
      if ((paramFloat > 0.0F) || ((paramFloat == 0.0F) && (f > 0.5F)))
        j = 0;
    while (true)
    {
      this.c.a(j, paramView.getTop());
      this.a.invalidate();
      return;
      j = -i;
      continue;
      j = this.a.getWidth();
      if ((paramFloat < 0.0F) || ((paramFloat == 0.0F) && (f > 0.5F)))
        j -= i;
    }
  }

  public final void a(View paramView, int paramInt)
  {
    int i = paramView.getWidth();
    float f;
    if (this.a.a(paramView, 3))
    {
      f = i + paramInt / i;
      this.a.a(paramView, f);
      if (f != 0.0F)
        break label78;
    }
    label78: for (int j = 4; ; j = 0)
    {
      paramView.setVisibility(j);
      this.a.invalidate();
      return;
      f = this.a.getWidth() - paramInt / i;
      break;
    }
  }

  public final boolean a(View paramView)
  {
    return (DrawerLayout.c(paramView)) && (this.a.a(paramView, this.b)) && (this.a.a(paramView) == 0);
  }

  public final int b(View paramView, int paramInt)
  {
    if (this.a.a(paramView, 3))
      return Math.max(-paramView.getWidth(), Math.min(paramInt, 0));
    int i = this.a.getWidth();
    return Math.max(i - paramView.getWidth(), Math.min(paramInt, i));
  }

  public final void b()
  {
    this.a.postDelayed(this.d, 160L);
  }

  public final void b(View paramView)
  {
    ((DrawerLayout.LayoutParams)paramView.getLayoutParams()).c = false;
    c();
  }

  public final int c(View paramView)
  {
    return paramView.getWidth();
  }

  public final int d(View paramView)
  {
    return paramView.getTop();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.6.1-SNAPSHOT
 */