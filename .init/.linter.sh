#!/bin/bash
cd /home/kavia/workspace/code-generation/task-management-backend-47754-47763/task_manager_backend
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

