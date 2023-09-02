import ex.api.ClusterAnalysisService;

module Implementation {
    requires Api;
    exports service;
    provides ClusterAnalysisService with service.KappaService, service.AccuracyService, service.BalancedAccuracy, service.F1ScoreService;
}