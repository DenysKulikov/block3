package com.solvd.android.components;

public enum BottomPanelButton {
    TUSKS("id/tasks_layout"),
    CALENDAR("id/calendar_layout"),
    MINE("id/mine_layout");
    private final String buttonId;

    private BottomPanelButton(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getButtonId() {
        return buttonId;
    }
}
