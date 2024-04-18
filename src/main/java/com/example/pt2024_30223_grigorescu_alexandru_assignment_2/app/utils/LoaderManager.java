package com.example.pt2024_30223_grigorescu_alexandru_assignment_2.app.utils;

public class LoaderManager {
    private final Integer maxLoading;
    private volatile Integer currentLoading = 0;
    private volatile Double loadingProgress = 0.0;

    public LoaderManager(Integer maxLoading) {
        this.maxLoading = maxLoading;
    }

    public synchronized void incrementLoading() {
        setCurrentLoading(currentLoading + 1);
    }

    public synchronized void setCurrentLoading(Integer currentLoading) {
        this.currentLoading = currentLoading;
        loadingProgress = (double) currentLoading / maxLoading;
    }

    public synchronized Double getLoadingProgress() {
        return loadingProgress;
    }
}
